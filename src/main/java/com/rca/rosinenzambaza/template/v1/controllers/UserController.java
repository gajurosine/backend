package com.rca.rosinenzambaza.template.v1.controllers;

import com.rca.rosinenzambaza.template.v1.annotations.ValidUUID;
import com.rca.rosinenzambaza.template.v1.dto.requests.CreateUserDTO;
import com.rca.rosinenzambaza.template.v1.dto.requests.UpdateUserDTO;
import com.rca.rosinenzambaza.template.v1.enums.EUserStatus;
import com.rca.rosinenzambaza.template.v1.models.User;
import com.rca.rosinenzambaza.template.v1.payload.ApiResponse;
import com.rca.rosinenzambaza.template.v1.services.UserService;
import com.rca.rosinenzambaza.template.v1.utils.Constants;
import com.rca.rosinenzambaza.template.v1.utils.ExceptionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@Slf4j
public class UserController {

    private final UserService userService;
    //    private final NotificationController notificationController;
    private final String ENTITY_NAME = "User";

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;

    }




    /**
     * @return ResponseEntity<ApiResponse>
     */
    @GetMapping(value = "/all")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<ApiResponse> getAllUsers() {
        try {
            log.info("Request to get all {}s", ENTITY_NAME);
            List<User> users = userService.getAllUsers();
            return new ResponseEntity<>(new ApiResponse(
                    true,
                    "Successfully fetched all users",
                    users
            ), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error occurred while getting all {}s", ENTITY_NAME, e);
            return ExceptionUtils.handleControllerExceptions(e);
        }
    }

    /**
     * @param uuid
     * @return
     */
    @GetMapping("/id/{uuid}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<ApiResponse> getUserById(@ValidUUID @PathVariable String uuid) {
        try {
            log.info("Request to get {} with id {}", ENTITY_NAME, uuid);
            User user = userService.getUserById(UUID.fromString(uuid));
            return new ResponseEntity<>(new ApiResponse(
                    true,
                    "Successfully fetched user with id " + uuid,
                    user
            ), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error occurred while getting {} with id {}", ENTITY_NAME, uuid, e);
            return ExceptionUtils.handleControllerExceptions(e);
        }
    }

    /**
     * @param userDTO
     * @return
     */

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createUser(@RequestBody CreateUserDTO userDTO) {
        try {
            log.info("Request to create {} with dto {}", ENTITY_NAME, userDTO);
            User createdUser = userService.createUser(userDTO);
            return new ResponseEntity<>(new ApiResponse(
                    true,
                    "Successfully created user",
                    createdUser
            ), HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error occurred while creating {} with dto {}", ENTITY_NAME, userDTO, e);
            return ExceptionUtils.handleControllerExceptions(e);
        }
    }



    /**
     * @param uuid
     * @param userDTO
     * @return
     */
    @PutMapping("/update/{uuid}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<ApiResponse> updateUser(@ValidUUID @PathVariable String uuid, @RequestBody UpdateUserDTO userDTO) {
        try {
            log.info("Request to update {} with dto {}", ENTITY_NAME, userDTO);
            User updatedUser = userService.updateUser(UUID.fromString(uuid), userDTO);
            return new ResponseEntity<>(new ApiResponse(
                    true,
                    "Successfully updated user",
                    updatedUser
            ), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error occurred while updating {} with dto {}", ENTITY_NAME, userDTO, e);
            return ExceptionUtils.handleControllerExceptions(e);
        }
    }

    /**
     * @param uuid
     * @return
     */
    @DeleteMapping("/void/{uuid}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<ApiResponse> voidUser(@ValidUUID @PathVariable String uuid) {
        try {
            log.info("Request to void {} with id {}", ENTITY_NAME, uuid);
            User deletedUser = userService.voidUser(UUID.fromString(uuid));
            return new ResponseEntity<>(new ApiResponse(
                    true,
                    "Successfully voided user",
                    deletedUser
            ), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error occurred while voiding {} with id {}", ENTITY_NAME, uuid, e);
            return ExceptionUtils.handleControllerExceptions(e);
        }
    }

    /**
     * @param uuid
     * @return
     */
    @DeleteMapping("/delete/{uuid}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<ApiResponse> deleteUser(@ValidUUID @PathVariable String uuid) {
        try {
            log.info("Request to delete {} with id {}", ENTITY_NAME, uuid);
            User deletedUser = userService.deleteUser(UUID.fromString(uuid));
            return new ResponseEntity<>(new ApiResponse(
                    true,
                    "Successfully deleted user",
                    deletedUser
            ), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error occurred while deleting {} with id {}", ENTITY_NAME, uuid, e);
            return ExceptionUtils.handleControllerExceptions(e);
        }
    }

    // other methods of a user







    // getting users by other parameters

    @GetMapping("/role/{role}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<ApiResponse> getUsersByRole(@ValidUUID @PathVariable String role) {
        try {
            log.info("Request to get {}s by role {}", ENTITY_NAME, role);
            List<User> users = userService.getUsersByRole(UUID.fromString(role));
            return new ResponseEntity<>(new ApiResponse(
                    true,
                    "Successfully fetched users by role",
                    users
            ), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error occurred while getting {}s by role {}", ENTITY_NAME, role, e);
            return ExceptionUtils.handleControllerExceptions(e);
        }
    }

    @GetMapping("/role/pagination/{role}")
    public ResponseEntity<ApiResponse> getUsersByRole(@ValidUUID @PathVariable String role,
                                                      @RequestParam(value = "page", defaultValue = Constants.DEFAULT_PAGE_NUMBER) int page,
                                                      @RequestParam(value = "limit", defaultValue = Constants.DEFAULT_PAGE_SIZE) int limit) {
        try {
            log.info("Request to get {}s y role {} among the page {} and limit {}", ENTITY_NAME, role, page, limit);
            Pageable pageable = PageRequest.of(page, limit);
            Page<User> users = userService.getUsersByRole(UUID.fromString(role), pageable);
            return new ResponseEntity<>(new ApiResponse(
                    true,
                    "Successfully fetched users by role",
                    users
            ), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error occurred while getting {}s by role {} among the page {} and limit {}", ENTITY_NAME, role, page, limit, e);
            return ExceptionUtils.handleControllerExceptions(e);
        }
    }






    @GetMapping("/account-status/{userStatus}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<ApiResponse> getUsersByAccountStatus(@PathVariable EUserStatus userStatus) {
        try {
            log.info("Request to get {}s by account status {}", ENTITY_NAME, userStatus);
            List<User> users = userService.getUsersByAccountStatus(userStatus);
            return new ResponseEntity<>(new ApiResponse(
                    true,
                    "Successfully fetched users by account status",
                    users
            ), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error occurred while getting {}s by account status {}", ENTITY_NAME, userStatus, e);
            return ExceptionUtils.handleControllerExceptions(e);
        }
    }


    @GetMapping("/account-status/pagination/{userStatus}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<ApiResponse> getUsersByAccountStatus(@PathVariable EUserStatus userStatus,
                                                               @RequestParam(value = "page", defaultValue = Constants.DEFAULT_PAGE_NUMBER) int page,
                                                               @RequestParam(value = "limit", defaultValue = Constants.DEFAULT_PAGE_SIZE) int limit) {
        try {
            log.info("Request to get {}s by account status {} among the page {} and limit {}", ENTITY_NAME, userStatus, page, limit);
            Pageable pageable = PageRequest.of(page, limit);
            Page<User> users = userService.getUsersByAccountStatus(userStatus, pageable);
            return new ResponseEntity<>(new ApiResponse(
                    true,
                    "Successfully fetched users by account status",
                    users
            ), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error occurred while getting {}s by account status {} among the page {} and limit {}", ENTITY_NAME, userStatus, page, limit, e);
            return ExceptionUtils.handleControllerExceptions(e);
        }
    }






}
