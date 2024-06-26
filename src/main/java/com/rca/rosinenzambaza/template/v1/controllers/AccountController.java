//package com.rca.mireilleumutoni.template.v1.controllers;
//
//import com.rca.mireilleumutoni.template.v1.dto.requests.CreateAccountDTO;
//import com.rca.mireilleumutoni.template.v1.payload.ApiResponse;
//import com.rca.mireilleumutoni.template.v1.serviceImpls.AccountServiceImpl;
//import com.rca.mireilleumutoni.template.v1.services.AccountService;
//import com.rca.mireilleumutoni.template.v1.utils.ExceptionUtils;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.UUID;
//
//@RestController
//@RequestMapping("api/v1/account")
//@RequiredArgsConstructor
//public class AccountController {
//
//    private  final AccountService accountService;
//
//    @PostMapping("/create")
//
//
//    ResponseEntity<ApiResponse> createAccount(@Validated @RequestBody CreateAccountDTO createAccountDTO){
//        try {
//            return ResponseEntity.ok(new ApiResponse(true, "The account  created successfully", this.accountService.createAccount(createAccountDTO)));
//
//    }catch (Exception e) {
//        return ExceptionUtils.handleControllerExceptions(e);
//    }
//    }
//    @GetMapping("/all")
//
//    ResponseEntity<ApiResponse> getAllAccounts(){
//        try{
//            return ResponseEntity.ok(new ApiResponse(true, "All accounts retrieved successfully", this.accountService.getAllAccounts()));
//        }catch (Exception e) {
//            return ExceptionUtils.handleControllerExceptions(e);
//        }
//    }
//    ResponseEntity<ApiResponse> getAccountById(@PathVariable("uuid") UUID accountId){
//        try{
//            return ResponseEntity.ok(new ApiResponse(true, "The account retrieved successfully", this.accountService.getAccountById(accountId)));
//        }catch (Exception e) {
//            return ExceptionUtils.handleControllerExceptions(e);
//        }
//    }
//    @PostMapping("/withdraw")
//    public ResponseEntity<ApiResponse> withdraw(@RequestParam UUID accountId, @RequestParam float amount) {
//        try {
//            accountService.withdraw(accountId, amount);
//            return ResponseEntity.ok(new ApiResponse(true, "Withdrawal successful"));
//        } catch (Exception e) {
//            return ExceptionUtils.handleControllerExceptions(e);
//        }
//    }
//
//    @PostMapping("/deposit")
//    public ResponseEntity<ApiResponse> deposit(@RequestParam UUID accountId, @RequestParam float amount) {
//        try {
//            accountService.deposit(accountId, amount);
//            return ResponseEntity.ok(new ApiResponse(true, "Withdrawal successful"));
//        } catch (Exception e) {
//            return ExceptionUtils.handleControllerExceptions(e);
//        }
//    }
//
//}


package com.rca.rosinenzambaza.template.v1.controllers;

import com.rca.rosinenzambaza.template.v1.dto.requests.CreateAccountDTO;
import com.rca.rosinenzambaza.template.v1.models.Customer;
import com.rca.rosinenzambaza.template.v1.payload.ApiResponse;
import com.rca.rosinenzambaza.template.v1.services.AccountService;
import com.rca.rosinenzambaza.template.v1.utils.ExceptionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createAccount(@Validated @RequestBody CreateAccountDTO createAccountDTO){
        try {
            return ResponseEntity.ok(new ApiResponse(true, "The account created successfully", accountService.createAccount(createAccountDTO)));
        } catch (Exception e) {
            return ExceptionUtils.handleControllerExceptions(e);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllAccounts(){
        try {
            return ResponseEntity.ok(new ApiResponse(true, "All accounts retrieved successfully", accountService.getAllAccounts()));
        } catch (Exception e) {
            return ExceptionUtils.handleControllerExceptions(e);
        }
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<ApiResponse> getAccountById(@PathVariable("uuid") UUID accountId){
        try {
            return ResponseEntity.ok(new ApiResponse(true, "The account retrieved successfully", accountService.getAccountById(accountId)));
        } catch (Exception e) {
            return ExceptionUtils.handleControllerExceptions(e);
        }
    }

    @PostMapping("/withdraw")
    public ResponseEntity<ApiResponse> withdraw(@RequestParam UUID accountId, @RequestParam float amount) {
        try {
            accountService.withdraw(accountId, amount);
            return ResponseEntity.ok(new ApiResponse(true, "Withdrawal successful"));
        } catch (Exception e) {
            return ExceptionUtils.handleControllerExceptions(e);
        }
    }

    @PostMapping("/deposit")
    public ResponseEntity<ApiResponse> deposit(@RequestParam UUID accountId, @RequestParam float amount) {
        try {
            accountService.deposit(accountId, amount);
            Customer customer = accountService.getCustomerByAccountId(accountId);
            return ResponseEntity.ok(new ApiResponse(true, "Deposit successful"));
        } catch (Exception e) {
            return ExceptionUtils.handleControllerExceptions(e);
        }
    }
}
