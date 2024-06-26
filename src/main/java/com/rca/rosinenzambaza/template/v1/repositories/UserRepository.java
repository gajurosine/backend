package com.rca.rosinenzambaza.template.v1.repositories;

import com.rca.rosinenzambaza.template.v1.enums.EUserStatus;
import com.rca.rosinenzambaza.template.v1.enums.EVisibility;
import com.rca.rosinenzambaza.template.v1.models.Role;
import com.rca.rosinenzambaza.template.v1.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findUserByEmail(String email);
    List<User> findAllByVisibility(EVisibility visibility);
    Page<User> findAllByVisibility(EVisibility visibility , Pageable pageable);
    Optional<User> findByIdAndVisibility(UUID id, EVisibility visibility);
    // getting users by other parameters
    List<User> findAllByRolesContainsAndVisibility(Role role, EVisibility visibility);
    Page<User> findAllByRolesContainsAndVisibility(Role role, EVisibility visibility , Pageable pageable);

    List<User> findAllByAccountStatusAndVisibility(EUserStatus userStatus , EVisibility visibility);
    Page<User> findAllByAccountStatusAndVisibility(EUserStatus userStatus , EVisibility visibility , Pageable pageable);

    // combined


    List<User> findAllByRolesContainsAndAccountStatusAndVisibility(Role role, EUserStatus userStatus , EVisibility visibility);
    Page<User> findAllByRolesContainsAndAccountStatusAndVisibility(Role role, EUserStatus userStatus , EVisibility visibility , Pageable pageable);




}
