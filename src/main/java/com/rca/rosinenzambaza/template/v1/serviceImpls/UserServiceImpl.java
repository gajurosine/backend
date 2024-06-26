    package com.rca.rosinenzambaza.template.v1.serviceImpls;

import com.rca.rosinenzambaza.template.v1.dto.requests.CreateUserDTO;
import com.rca.rosinenzambaza.template.v1.dto.requests.UpdateUserDTO;
import com.rca.rosinenzambaza.template.v1.enums.EUserStatus;
import com.rca.rosinenzambaza.template.v1.enums.EVisibility;
import com.rca.rosinenzambaza.template.v1.exceptions.BadRequestException;
import com.rca.rosinenzambaza.template.v1.exceptions.NotFoundException;
import com.rca.rosinenzambaza.template.v1.exceptions.UnAuthorizedException;
import com.rca.rosinenzambaza.template.v1.models.Person;
import com.rca.rosinenzambaza.template.v1.models.Role;
import com.rca.rosinenzambaza.template.v1.models.User;
import com.rca.rosinenzambaza.template.v1.repositories.RoleRepository;
import com.rca.rosinenzambaza.template.v1.repositories.UserRepository;
import com.rca.rosinenzambaza.template.v1.security.user.UserSecurityDetails;
import com.rca.rosinenzambaza.template.v1.services.RoleService;
import com.rca.rosinenzambaza.template.v1.services.UserService;
import com.rca.rosinenzambaza.template.v1.utils.ExceptionUtils;
import com.rca.rosinenzambaza.template.v1.utils.Hash;
import com.rca.rosinenzambaza.template.v1.utils.ServiceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

    @Component
    @Service
    public class UserServiceImpl implements UserService {
        private final UserRepository userRepository;
        private final RoleRepository roleRepository;
        private final RoleService roleService;




        @Autowired
        public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, RoleRepository roleRepository1, RoleService roleService) {
            this.userRepository = userRepository;

            this.roleRepository = roleRepository1;
            this.roleService = roleService;
        }

        public boolean isUserLoggedIn() {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            return !authentication.getPrincipal().equals("anonymousUser");
        }

        public User getLoggedInUser() {
            UserSecurityDetails userSecurityDetails;
            // Retrieve the currently authenticated user from the SecurityContextHolder
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication != null) {
                userSecurityDetails = (UserSecurityDetails) authentication.getPrincipal();
                return this.userRepository.findUserByEmail(userSecurityDetails.getUsername()).orElseThrow(() -> new UnAuthorizedException("You are not authorized! please login"));
            } else {
                throw new UnAuthorizedException("You are not authorized! please login");
            }
        }

        @Override
        public List<User> getAllUsers() {
            try {
                return userRepository.findAllByVisibility(EVisibility.VISIBLE);
            } catch (Exception e) {
                ExceptionUtils.handleServiceExceptions(e);
                return null;
            }
        }

        @Override
        public Page<User> getAllUsers(Pageable pageable) {
            try {
                return userRepository.findAllByVisibility(EVisibility.VISIBLE, pageable);
            } catch (Exception e) {
                ExceptionUtils.handleServiceExceptions(e);
                return null;
            }
        }

        @Override
        public User getUserById(UUID uuid) {
            try {
                User user = userRepository
                        .findById(uuid)
                        .orElseThrow(() -> new NotFoundException("The user with the given id was not found"));
                if (ServiceUtils.isUserDeleted(user)) {
                    throw new NotFoundException("The user with the given id was not found");
                } else {
                    return user;
                }
            } catch (Exception e) {
                ExceptionUtils.handleServiceExceptions(e);
                return null;
            }
        }

        @Override
        public User getUserByEmail(String email) {
            try {
                User user = userRepository.findUserByEmail(email).orElseThrow(() -> new NotFoundException("The user with the given email was not found"));
                if (ServiceUtils.isUserDeleted(user)) {
                    throw new NotFoundException("The user with the given email was not found");
                } else {
                    return user;
                }
            } catch (Exception e) {
                ExceptionUtils.handleServiceExceptions(e);
                return null;
            }
        }

        @Override
        public User createUser(CreateUserDTO user) {
            try {
                User userByEmail = userRepository.findUserByEmail(user.getEmail()).orElse(null);
                if (userByEmail != null) {
                    throw new BadRequestException("The user with the given email already exists");
                }
                User userEntity = new User();
                userEntity.setEmail(user.getEmail());
                userEntity.setUsername(user.getUsername());
                userEntity.setFirstName(user.getFirstName());
                userEntity.setLastName(user.getLastName());
                userEntity.setPassword(Hash.hashPassword(user.getPassword()));

                Role role = roleRepository.findById(user.getRoleId()).orElseThrow(() -> new NotFoundException("The role with the given id was nbr found"));

//                DataLocation dataLocation = dataLocationService.getById(user.getDataLocationId());

                userEntity.getRoles().add(role);
//                userEntity.setAbstractLocation(dataLocation);
                userEntity.setAccountStatus(EUserStatus.ACTIVE);
//                mailService.sendInvitationEmail(userEntity);
                userRepository.save(userEntity);
                return userEntity;
            } catch (Exception e) {
                ExceptionUtils.handleServiceExceptions(e);
                return null;
            }
        }

        @Override
        @Transactional
        public User updateUser(UUID uuid, UpdateUserDTO user) {
            try {
                User userEntity = userRepository.findById(uuid).orElseThrow(() -> new NotFoundException("The user with the provided id was not found"));
                Role role = roleService.getRoleById(user.getRoleId());
//                DataLocation dataLocation = dataLocationService.getById(user.getDataLocationId());
                if (ServiceUtils.isUserDeleted(userEntity)) {
                    throw new NotFoundException("The user with the provided id was not found");
                }
//                userEntity.setAbstractLocation(dataLocation);
                userEntity.getRoles().add(role);
                userEntity.setFirstName(user.getFirstName());
                userEntity.setLastName(user.getLastName());
                userEntity.setUsername(user.getUsername());
                userEntity.setEmail(user.getEmail());
                return userEntity;
            } catch (Exception e) {
                ExceptionUtils.handleServiceExceptions(e);
                return null;
            }
        }

        @Override
        public User voidUser(UUID uuid) {
            try {
                User user = userRepository
                        .findById(uuid)
                        .orElseThrow(() -> new NotFoundException("The user with the given id was not found"));
                user.setVisibility(EVisibility.VOIDED);
                userRepository.save(user);
                return user;
            } catch (Exception e) {
                ExceptionUtils.handleServiceExceptions(e);
                return null;
            }
        }

        @Override
        public User deleteUser(UUID uuid) {
            try {
                User user = userRepository
                        .findById(uuid)
                        .orElseThrow(() -> new NotFoundException("The user with the given id was not found"));
                userRepository.delete(user);
                return user;
            } catch (Exception e) {
                ExceptionUtils.handleServiceExceptions(e);
                return null;
            }
        }

        public boolean isNotUnique(Person user) {
            try {
                return this.userRepository.findUserByEmail(user.getEmail()).isPresent();
            } catch (Exception e) {
                ExceptionUtils.handleServiceExceptions(e);
                return false;
            }
        }

        public boolean validateUserEntry(Person user) {
            if (isNotUnique(user)) {
                String errorMessage = "The user with the email: " + user.getEmail() +
                        "  or national id: " + user.getNationalId() +
                        " or phone number: " + user.getPhoneNumber() +
                        " already exists";
                throw new BadRequestException(errorMessage);
            } else {
                return true;
            }
        }






        // getting users by other parameters implementation

        @Override
        public List<User> getUsersByRole(UUID role) {
            try {
                Role role1 = roleRepository.findById(role).orElseThrow(() -> new NotFoundException("The role with the given id was not found"));
                return userRepository.findAllByRolesContainsAndVisibility(role1, EVisibility.VISIBLE);
            } catch (Exception e) {
                ExceptionUtils.handleServiceExceptions(e);
                return null;
            }
        }

        @Override
        public Page<User> getUsersByRole(UUID role, Pageable pageable) {
            try {
                Role role1 = roleRepository.findById(role).orElseThrow(() -> new NotFoundException("The role with the given id was not found"));
                return userRepository.findAllByRolesContainsAndVisibility(role1, EVisibility.VISIBLE, pageable);
            } catch (Exception e) {
                ExceptionUtils.handleServiceExceptions(e);
                return null;
            }
        }





        @Override
        public List<User> getUsersByAccountStatus(EUserStatus userStatus) {
            try {
                return userRepository.findAllByAccountStatusAndVisibility(userStatus, EVisibility.VISIBLE);
            } catch (Exception e) {
                ExceptionUtils.handleServiceExceptions(e);
                return null;
            }
        }

        @Override
        public Page<User> getUsersByAccountStatus(EUserStatus userStatus, Pageable pageable) {
            try {
                return userRepository.findAllByAccountStatusAndVisibility(userStatus, EVisibility.VISIBLE, pageable);
            } catch (Exception e) {
                ExceptionUtils.handleServiceExceptions(e);
                return null;
            }
        }












    }


