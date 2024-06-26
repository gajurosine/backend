package com.rca.rosinenzambaza.template.v1.services;

import com.rca.rosinenzambaza.template.v1.dto.requests.CreateAccountDTO;
import com.rca.rosinenzambaza.template.v1.models.Account;
import com.rca.rosinenzambaza.template.v1.models.Customer;

import java.util.List;
import java.util.UUID;

public interface AccountService {
    Account createAccount(CreateAccountDTO createAccountDTO);
    Account getAccountById(UUID accountId);
    Account getAccountByCustomerId(UUID customerId);
  List   <Account> getAllAccounts();
    void deposit(UUID accountId, float amount);
    void withdraw(UUID accountId, float amount);

    Customer getCustomerByAccountId(UUID accountId);
}
