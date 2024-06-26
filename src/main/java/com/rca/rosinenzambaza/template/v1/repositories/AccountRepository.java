package com.rca.rosinenzambaza.template.v1.repositories;

import com.rca.rosinenzambaza.template.v1.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {

    Optional<Account> findAccountById(UUID accountId);
    Optional<Account> findAccountsByCustomerId(UUID accountId);

}
