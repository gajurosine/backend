package com.rca.rosinenzambaza.template.v1.serviceImpls;

import com.rca.rosinenzambaza.template.v1.dto.requests.CreateAccountDTO;
import com.rca.rosinenzambaza.template.v1.enums.AccountType;
import com.rca.rosinenzambaza.template.v1.mailHandling.MailService;
import com.rca.rosinenzambaza.template.v1.models.Account;
import com.rca.rosinenzambaza.template.v1.models.Customer;
import com.rca.rosinenzambaza.template.v1.repositories.AccountRepository;
import com.rca.rosinenzambaza.template.v1.repositories.CustomerRepository;
import com.rca.rosinenzambaza.template.v1.services.AccountService;
import com.rca.rosinenzambaza.template.v1.services.CustomerService;
import com.rca.rosinenzambaza.template.v1.utils.ExceptionUtils;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    @Lazy
    private final CustomerService customerService;
    private final CustomerRepository customerRepository;
    private  final MailService mailService ;


    @Override
    public Account createAccount(CreateAccountDTO createAccountDTO) {
        try {
            Customer customer = customerRepository.findById(createAccountDTO.getCustomerId()).orElseThrow(() -> new RuntimeException("Customer not found"));
            Account account = new Account();
            account.setAccountNumber(createAccountDTO.getAccountNumber());
            account.setAccountType(AccountType.valueOf(createAccountDTO.getAccountType()));
            account.setBalance(createAccountDTO.getBalance());
            account.setAmount(createAccountDTO.getAmount());
            account.setCustomer(customerService.getCustomerById(createAccountDTO.getCustomerId()));
            return accountRepository.save(account);
        } catch (Exception e) {
            ExceptionUtils.handleServiceExceptions(e);
        }
        return null;
    }

    @Override
    public Account getAccountById(UUID accountId) {
        try {
            return accountRepository.findById(accountId)
                    .orElseThrow(() -> new RuntimeException("Account not found"));
        } catch (Exception e) {
            ExceptionUtils.handleServiceExceptions(e);
        }
        return null;
    }

    @Override
    public Account getAccountByCustomerId(UUID customerId) {
        try {
            return accountRepository.findAccountsByCustomerId(customerId)
                    .orElseThrow(() -> new RuntimeException("Account not found"));
        } catch (Exception e) {
            ExceptionUtils.handleServiceExceptions(e);
        }
        return null;
    }

    @Override
    public List<Account> getAllAccounts() {
        try {
            return accountRepository.findAll();
        } catch (Exception e) {
            ExceptionUtils.handleServiceExceptions(e);
        }
        return null;
    }

    @Override
    public void withdraw(UUID accountId, float amount) {
        try {
            System.out.println("WITHDRAWAL");
            Account account = accountRepository.findById(accountId)
                    .orElseThrow(() -> new RuntimeException("Account not found"));

            Customer customer = account.getCustomer();

            if (account.getBalance() < amount) {
                throw new RuntimeException("Insufficient balance");
            }

            account.setBalance(account.getBalance() - amount);

            String subject = "Withdrawal Notification";
            String body = String.format("Dear %s, your withdrawal of %.2f from account %s has been completed successfully.",
                    customer.getFirstName(), amount, account.getId());

            UUID uuid = UUID.randomUUID();
            System.out.println("RANDOM 1: " + UUID.randomUUID());
            account.setMessageId(uuid);
            System.out.println("RANDOM 2: " + UUID.randomUUID());
            account.setMessage( "Withdraw of " + amount + " to account " + account.getId() + " has been completed successfully."  );
            accountRepository.save(account);
            mailService.sendEmail(customer.getEmail(), "Withdrawal", "Dear " + customer.getFirstName() + ", your withdrawal of " + amount + " from account " + account.getId() + " has been completed successfully.");


        } catch (Exception e) {
            ExceptionUtils.handleServiceExceptions(e);
        }
    }

    @Override
    public Customer getCustomerByAccountId(UUID accountId) {
        try {
            Account account = accountRepository.findById(accountId)
                    .orElseThrow(() -> new RuntimeException("Account not found"));
            return account.getCustomer();
        } catch (Exception e) {
            ExceptionUtils.handleServiceExceptions(e);
        }
        return null;
    }

    @Override
    public void deposit(UUID accountId, float amount) {
        try {
            Account account = accountRepository.findById(accountId)
                    .orElseThrow(() -> new RuntimeException("Account not found"));


            Customer customer = account.getCustomer();
            String subject = "Deposit Notification";
            String body = String.format("Dear %s, your deposit of %.2f to account %s has been completed successfully.", customer.getFirstName(), amount, account.getId());
            UUID uuid = UUID.randomUUID();
            account.setBalance(account.getBalance() + amount);
            System.out.println("RANDOM 1: " + UUID.randomUUID());
            account.setMessageId(uuid);
            System.out.println("RANDOM 2: " + UUID.randomUUID());
            account.setMessage( "Deposit of " + amount + " to account " + account.getId() + " has been completed successfully."  );
            accountRepository.save(account);
            mailService.sendEmail(customer.getEmail(), "Deposit", "Dear " + customer.getFirstName() + ", your deposit of " + amount + " to account " + account.getId() + " has been completed successfully.");
        } catch (Exception e) {
            ExceptionUtils.handleServiceExceptions(e);
        }
    }
}
