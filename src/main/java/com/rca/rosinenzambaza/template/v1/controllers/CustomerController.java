package com.rca.rosinenzambaza.template.v1.controllers;

import com.rca.rosinenzambaza.template.v1.dto.requests.CreateCustomerDTO;
import com.rca.rosinenzambaza.template.v1.payload.ApiResponse;
import com.rca.rosinenzambaza.template.v1.services.CustomerService;
import com.rca.rosinenzambaza.template.v1.utils.ExceptionUtils;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {
    private  final CustomerService customerService;


    /**
     *
     * @param createCustomerDTO
     * @return
     */
    @PostMapping("/create")

    ResponseEntity<ApiResponse> createCustomer(@Validated  @RequestBody CreateCustomerDTO createCustomerDTO){
        try {
            return ResponseEntity.ok(new ApiResponse(true, "The customer  created successfully", this.customerService.createCustomer(createCustomerDTO)));

    }catch (Exception e) {
        return ExceptionUtils.handleControllerExceptions(e);
    }
    }
    /**
     *
     * @return
     */

    @GetMapping("/all")

    ResponseEntity<ApiResponse> getAllCustomers(){
        try{
            return ResponseEntity.ok(new ApiResponse(true, "All customers retrieved successfully", this.customerService.getAllCustomers()));
        }catch (Exception e) {
            return ExceptionUtils.handleControllerExceptions(e);
        }
    }
    /**
     *
     * @param customerId
     * @return
     */
    @GetMapping("/{uuid}")
    ResponseEntity<ApiResponse> getCustomerById(@PathVariable("uuid") UUID customerId){
        try{
            return ResponseEntity.ok(new ApiResponse(true, "The customer retrieved successfully", this.customerService.getCustomerById(customerId)));
        }catch (Exception e) {
            return ExceptionUtils.handleControllerExceptions(e);
        }
    }
    /**
     *
     * @param customerId
     * @return
     */
    @PutMapping("update/{uuid}")
    ResponseEntity<ApiResponse> updateCustomer(@PathVariable("uuid") UUID customerId, @Validated @RequestBody CreateCustomerDTO createCustomerDTO){
        try{
            return ResponseEntity.ok(new ApiResponse(true, "The customer updated successfully", this.customerService.updateCustomer(customerId, createCustomerDTO)));
        }catch (Exception e) {
            return ExceptionUtils.handleControllerExceptions(e);
        }
    }

    @PostMapping("/transfer")
    public ResponseEntity<ApiResponse> transferAmount(@RequestParam UUID senderId, @RequestParam UUID receiverId, @RequestParam float amount) {
        try {
            customerService.transferAmount(senderId, receiverId, amount);
            return ResponseEntity.ok(new ApiResponse(true, "The amount transferred successfully"));
        } catch (Exception e) {
            return ExceptionUtils.handleControllerExceptions(e);
        }
    }
}
