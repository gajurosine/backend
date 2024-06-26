package com.rca.rosinenzambaza.template.v1.repositories;

import com.rca.rosinenzambaza.template.v1.models.Withdraw;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface WithdrawRepository extends JpaRepository<Withdraw, UUID>{
}
