package com.rca.rosinenzambaza.template.v1.services;

import com.rca.rosinenzambaza.template.v1.dto.requests.CreateCustomerDTO;
import com.rca.rosinenzambaza.template.v1.models.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerService {
    Customer createCustomer(CreateCustomerDTO createCustomerDTO);
    Customer getCustomerById(UUID customerId);

    List<Customer> getAllCustomers();

    Customer updateCustomer (UUID uuid, CreateCustomerDTO createCustomerDTO);

    void transferAmount(UUID customerId, UUID accountId, float amount);


}
