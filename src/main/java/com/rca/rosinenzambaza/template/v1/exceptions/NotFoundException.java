package com.rca.rosinenzambaza.template.v1.exceptions;

import com.rca.rosinenzambaza.template.v1.dto.responses.ErrorResponse;
import com.rca.rosinenzambaza.template.v1.dto.responses.Response;
import com.rca.rosinenzambaza.template.v1.enums.ResponseType;
import com.rca.rosinenzambaza.template.v1.payload.ApiSecondResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException{

    public NotFoundException(String message){
        super(message);
    }

    public ResponseEntity<ApiSecondResponse> getResponse(){
        List<String> details = new ArrayList<>();
        details.add(super.getMessage());
        ErrorResponse errorResponse = new ErrorResponse().setMessage("Failed to get a resource").setDetails(details);
        Response<ErrorResponse> response = new Response<>();
        response.setType(ResponseType.RESOURCE_NOT_FOUND);
        response.setPayload(errorResponse);
        return  ResponseEntity.ok(ApiSecondResponse.fail(this.getMessage()));
    }
}
