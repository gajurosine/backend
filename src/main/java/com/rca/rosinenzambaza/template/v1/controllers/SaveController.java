package com.rca.rosinenzambaza.template.v1.controllers;


import com.rca.rosinenzambaza.template.v1.dto.requests.CreateSaveDTO;
import com.rca.rosinenzambaza.template.v1.payload.ApiResponse;
import com.rca.rosinenzambaza.template.v1.services.SaveService;
import com.rca.rosinenzambaza.template.v1.utils.ExceptionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/save")
@RequiredArgsConstructor
public class SaveController {
    private final SaveService saveService;

    /**
     *
     * @param saveDTO
     * @return
     */

    @PostMapping("/create")

    ResponseEntity<ApiResponse> createSave(@Validated @RequestBody CreateSaveDTO saveDTO) {
        try {
            return ResponseEntity.ok(new ApiResponse(true, "The save  created successfully", this.saveService.createSave(saveDTO)));
        } catch (Exception e) {
            return ExceptionUtils.handleControllerExceptions(e);
        }

    }

    @GetMapping("/{uuid}")
    public ResponseEntity<ApiResponse> getSaveById(@PathVariable("uuid") UUID saveId){
        try {
            return ResponseEntity.ok(new ApiResponse(true, "The account retrieved successfully", saveService.getSaveById(saveId)));
        } catch (Exception e) {
            return ExceptionUtils.handleControllerExceptions(e);
        }
    }

}