//package com.rca.mireilleumutoni.template.v1.serviceImpls;
//
//import com.rca.mireilleumutoni.template.v1.dto.requests.CreateCustomerDTO;
//import com.rca.mireilleumutoni.template.v1.models.Account;
//import com.rca.mireilleumutoni.template.v1.models.Customer;
//import com.rca.mireilleumutoni.template.v1.repositories.AccountRepository;
//import com.rca.mireilleumutoni.template.v1.repositories.CustomerRepository;
//import com.rca.mireilleumutoni.template.v1.services.AccountService;
//import com.rca.mireilleumutoni.template.v1.services.CustomerService;
//import com.rca.mireilleumutoni.template.v1.utils.ExceptionUtils;
//import lombok.AllArgsConstructor;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Lazy;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.UUID;
//@Service
//@RequiredArgsConstructor
//public class CustomerServiceImpl implements CustomerService {
//    private  final CustomerRepository customerRepository;
//    @Lazy
//    private  final AccountService accountService;
//    private  final AccountRepository accountRepository;
//    @Override
//    public Customer createCustomer(CreateCustomerDTO createCustomerDTO) {
//        try {
//            Account account = accountRepository.findAccountById(createCustomerDTO.getAccountId())
//                    .orElseThrow(() -> new RuntimeException("Account not found"));
//
//            List<Account> accounts = new ArrayList<>();
//            accounts.add(account);
//
//            Customer customer = new Customer();
//            customer.setAccounts(accounts);
//            customer.setFirstName(createCustomerDTO.getFirstName());
//            customer.setLastName(createCustomerDTO.getLastName());
//            customer.setMobile(createCustomerDTO.getMobile());
//            customer.setEmail(createCustomerDTO.getEmail());
//            customer.setDob(createCustomerDTO.getDob());
//            customer.setBalance(createCustomerDTO.getBalance());
//            customer.setLastUpdateDateTime(createCustomerDTO.getLastUpdateDateTime());
//
//            return customerRepository.save(customer);
//        } catch (Exception e) {
//            ExceptionUtils.handleServiceExceptions(e);
//        }
//        return null;
//    }
//    @Override
//    public Customer getCustomerById(UUID customerId) {
//        try {
//            return customerRepository.findById(customerId)
//                    .orElseThrow(() -> new RuntimeException("Customer not found"));
//        } catch (Exception e) {
//            ExceptionUtils.handleServiceExceptions(e);
//        }
//        return null;
//    }
//
//    @Override
//    public List<Customer> getAllCustomers() {
//        try
//        {
//            return customerRepository.findAll();
//        } catch (Exception e) {
//            ExceptionUtils.handleServiceExceptions(e);
//        }
//        return null;
//    }
//
//    @Override
//    public Customer updateCustomer(UUID uuid, CreateCustomerDTO createCustomerDTO) {
//        try {
//            Account account = accountRepository.findAccountById(createCustomerDTO.getAccountId())
//                    .orElseThrow(() -> new RuntimeException("Account not found"));
//
//            List<Account> accounts = new ArrayList<>();
//            accounts.add(account);
//
//            Customer customer = new Customer();
//            customer.setAccounts(accounts);
//            customer.setFirstName(createCustomerDTO.getFirstName());
//            customer.setLastName(createCustomerDTO.getLastName());
//            customer.setMobile(createCustomerDTO.getMobile());
//            customer.setEmail(createCustomerDTO.getEmail());
//            customer.setDob(createCustomerDTO.getDob());
//            customer.setBalance(createCustomerDTO.getBalance());
//            customer.setLastUpdateDateTime(createCustomerDTO.getLastUpdateDateTime());
//
//            return customerRepository.save(customer);
//        } catch (Exception e) {
//            ExceptionUtils.handleServiceExceptions(e);
//        }
//        return null;
//    }
//
//    @Override
//    public void transferAmount(UUID customerId, UUID accountId, float amount) {
//        // Find the customer
//        Customer customer = customerRepository.findById(customerId)
//                .orElseThrow(() -> new RuntimeException("Customer not found"));
//
//        // Find the account
//        Account account = accountRepository.findAccountById(accountId)
//                .orElseThrow(() -> new RuntimeException("Account not found"));
//
//        // Check if the customer has enough balance
//        if (customer.getBalance() < amount) {
//            throw new RuntimeException("Insufficient balance");
//        }
//
//        // Perform the transfer
//        customer.setBalance(customer.getBalance() - amount);
//        account.setBalance(account.getBalance() + amount);
//
//        // Save the updated customer and account
//        customerRepository.save(customer);
//        accountRepository.save(account);
//    }
//
//
//
//
//}


package com.rca.rosinenzambaza.template.v1.serviceImpls;

import com.rca.rosinenzambaza.template.v1.dto.requests.CreateCustomerDTO;
import com.rca.rosinenzambaza.template.v1.models.Account;
import com.rca.rosinenzambaza.template.v1.models.Customer;
import com.rca.rosinenzambaza.template.v1.repositories.AccountRepository;
import com.rca.rosinenzambaza.template.v1.repositories.CustomerRepository;
import com.rca.rosinenzambaza.template.v1.services.CustomerService;
import com.rca.rosinenzambaza.template.v1.utils.ExceptionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;

    @Override
    @Transactional
    public Customer createCustomer(CreateCustomerDTO createCustomerDTO) {
        try {





            Customer customer = new Customer();

            customer.setFirstName(createCustomerDTO.getFirstName());
            customer.setLastName(createCustomerDTO.getLastName());
            customer.setMobile(createCustomerDTO.getMobile());
            customer.setEmail(createCustomerDTO.getEmail());
            customer.setDob(createCustomerDTO.getDob());
            customer.setBalance(createCustomerDTO.getBalance());
            customer.setLastUpdateDateTime(createCustomerDTO.getLastUpdateDateTime());

            return customerRepository.save(customer);
        } catch (Exception e) {
            ExceptionUtils.handleServiceExceptions(e);
        }
        return null;
    }

    @Override
    public Customer getCustomerById(UUID customerId) {
        try {
            return customerRepository.findById(customerId)
                    .orElseThrow(() -> new RuntimeException("Customer not found"));
        } catch (Exception e) {
            ExceptionUtils.handleServiceExceptions(e);
        }
        return null;
    }

    @Override
    public List<Customer> getAllCustomers() {
        try {
            return customerRepository.findAll();
        } catch (Exception e) {
            ExceptionUtils.handleServiceExceptions(e);
        }
        return null;
    }

    @Override
    @Transactional
    public Customer updateCustomer(UUID customerId, CreateCustomerDTO createCustomerDTO) {
        try {
            Customer customer = customerRepository.findById(customerId)
                    .orElseThrow(() -> new RuntimeException("Customer not found"));



            customer.setFirstName(createCustomerDTO.getFirstName());
            customer.setLastName(createCustomerDTO.getLastName());
            customer.setMobile(createCustomerDTO.getMobile());
            customer.setEmail(createCustomerDTO.getEmail());
            if (createCustomerDTO.getDob().after(Date.valueOf(LocalDate.now()))){
                throw new RuntimeException("Invalid date of birth");
            }
            customer.setDob(createCustomerDTO.getDob());
            customer.setBalance(createCustomerDTO.getBalance());
            customer.setLastUpdateDateTime(createCustomerDTO.getLastUpdateDateTime());

            return customerRepository.save(customer);
        } catch (Exception e) {
            ExceptionUtils.handleServiceExceptions(e);
        }
        return null;
    }

    @Override
    @Transactional
    public void transferAmount(UUID sendId, UUID receiverId, float amount) {
        try {


            Account account = accountRepository.findById(sendId)
                    .orElseThrow(() -> new RuntimeException("Account not found"));

            Account account1 = accountRepository.findById(receiverId)
                    .orElseThrow(() -> new RuntimeException("Customer not found"));

            if (account.getBalance() < amount) {
                throw new RuntimeException("Insufficient balance");
            }

            account1.setBalance(account1.getBalance() + amount);
            account.setBalance(account.getBalance() - amount);
            UUID uuid = UUID.randomUUID();
            UUID uuid1 = UUID.randomUUID();
            System.out.println("RANDOM 1: " + UUID.randomUUID());

            account.setMessageId(uuid);
            account1.setMessageId(uuid1);
            account.setMessage( "transfer of " + amount + " to account " + account.getId() + " has been completed successfully."  );


            accountRepository.save(account1);
            accountRepository.save(account);
        } catch (Exception e) {
            ExceptionUtils.handleServiceExceptions(e);
        }

    }
}
