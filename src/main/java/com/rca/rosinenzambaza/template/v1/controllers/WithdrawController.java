package com.rca.rosinenzambaza.template.v1.controllers;


import com.rca.rosinenzambaza.template.v1.dto.requests.CreateWithdrawDTO;
import com.rca.rosinenzambaza.template.v1.payload.ApiResponse;
import com.rca.rosinenzambaza.template.v1.services.WithdrawService;
import com.rca.rosinenzambaza.template.v1.utils.ExceptionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/withdraw")
@RequiredArgsConstructor
public class WithdrawController {
    private final WithdrawService withdrawService;

    @PostMapping("create")
    ResponseEntity<ApiResponse> createWithdraw(@Validated @RequestBody CreateWithdrawDTO withdrawDTO) {
        try {
            return ResponseEntity.ok(new ApiResponse(true, "The withdraw  created successfully", this.withdrawService.createWithdraw(withdrawDTO)));
        } catch (Exception e) {
            return ExceptionUtils.handleControllerExceptions(e);
        }

    }
    /**
     *
     * @param withdrawId
     * @return
     */
    @GetMapping("/{uuid}")
    public ResponseEntity<ApiResponse> getWithdrawById(@PathVariable("uuid") UUID withdrawId){
        try {
            return ResponseEntity.ok(new ApiResponse(true, "The account retrieved successfully", withdrawService.getWithdrawById(withdrawId)));
        } catch (Exception e) {
            return ExceptionUtils.handleControllerExceptions(e);
        }
    }


}
