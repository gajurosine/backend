package com.rca.rosinenzambaza.template.v1.serviceImpls;

import com.rca.rosinenzambaza.template.v1.dto.requests.CreateWithdrawDTO;
import com.rca.rosinenzambaza.template.v1.models.Account;
import com.rca.rosinenzambaza.template.v1.models.Customer;
import com.rca.rosinenzambaza.template.v1.models.Withdraw;
import com.rca.rosinenzambaza.template.v1.repositories.AccountRepository;
import com.rca.rosinenzambaza.template.v1.repositories.CustomerRepository;
import com.rca.rosinenzambaza.template.v1.repositories.WithdrawRepository;
import com.rca.rosinenzambaza.template.v1.services.WithdrawService;
import com.rca.rosinenzambaza.template.v1.utils.ExceptionUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class WithdrawServiceImpl implements WithdrawService {
    private  final WithdrawRepository withdrawRepository;
    private  final AccountRepository accountRepository ;
    private  final CustomerRepository customerRepository ;
    @Override
    public Withdraw createWithdraw(CreateWithdrawDTO createWithdrawDTO) {
        try {
            Account account = accountRepository.findById(createWithdrawDTO.getAccountId()).orElseThrow(() -> new RuntimeException("Account not found"));
            Customer customer = customerRepository.findById(createWithdrawDTO.getCustomerId()).orElseThrow(() -> new RuntimeException("Customer not found"));
            Withdraw withdraw = new Withdraw();
            withdraw.setAccount(account);
            withdraw.setCustomer(customer);
            withdraw.setAmount(withdraw.getAmount());
            withdraw.setBankingTime(withdraw.getBankingTime());
            return withdrawRepository.save(withdraw);
        } catch (Exception e) {
            ExceptionUtils.handleServiceExceptions(e);
        }

        return null;
    }

    @Override
    public Withdraw getWithdrawById(UUID withdrawId) {
        try {
            return withdrawRepository.findById(withdrawId)
                    .orElseThrow(() -> new RuntimeException("Withdraw not found"));
        } catch (Exception e) {
            ExceptionUtils.handleServiceExceptions(e);
        }
        return null;
    }

    @Override
    public Withdraw updateWithdraw(UUID uuid, CreateWithdrawDTO createWithdrawDTO) {
        try {
            Withdraw withdraw = withdrawRepository.findById(uuid).orElseThrow(() -> new RuntimeException("Withdraw not found"));
            Account account = accountRepository.findById(createWithdrawDTO.getAccountId()).orElseThrow(() -> new RuntimeException("Account not found"));
            Customer customer = customerRepository.findById(createWithdrawDTO.getCustomerId()).orElseThrow(() -> new RuntimeException("Customer not found"));
            withdraw.setAccount(account);
            withdraw.setCustomer(customer);
            withdraw.setAmount(withdraw.getAmount());
            withdraw.setBankingTime(withdraw.getBankingTime());
            return withdrawRepository.save(withdraw);
        } catch (Exception e) {
            ExceptionUtils.handleServiceExceptions(e);
        }
        return null;
    }
}
