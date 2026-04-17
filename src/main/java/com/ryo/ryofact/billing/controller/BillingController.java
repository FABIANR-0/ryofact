package com.ryo.ryofact.billing.controller;

import com.ryo.ryofact.billing.dto.BillingCreate;
import com.ryo.ryofact.billing.dto.BillingRequest;
import com.ryo.ryofact.billing.dto.api.Factura;
import com.ryo.ryofact.billing.service.BillingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bill")
@AllArgsConstructor
public class BillingController {

    private final BillingService billingService;

    @PostMapping("/obtain-invoice")
    @Operation(
            description = "get all invoices of client by client identifier or billing identifier",
            security = @SecurityRequirement(name = "api-key")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success"),
            @ApiResponse(responseCode = "400", description = "bad request"),
            @ApiResponse(responseCode = "404", description = "not found"),
            @ApiResponse(responseCode = "500", description = "internal server error"),
            @ApiResponse(responseCode = "401", description = "unauthorized")
    })
    public ResponseEntity<List<Factura>> obtainInvoice(@RequestBody @Valid BillingRequest request) {
        List<Factura> responses = billingService.obtainInvoice(request);
        return ResponseEntity.ok(responses);
    }

    @PostMapping("/save-payment")
    @Operation(
            description = "save payment of client by crediservir",
            security = @SecurityRequirement(name = "api-key")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "created"),
            @ApiResponse(responseCode = "400", description = "bad request"),
            @ApiResponse(responseCode = "409", description = "conflict"),
            @ApiResponse(responseCode = "500", description = "internal server error"),
            @ApiResponse(responseCode = "401", description = "unauthorized")
    })
    public ResponseEntity<?> savePayment(@RequestBody @Valid BillingCreate request) {
        String response = billingService.savePayment(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("response", response));
    }
}
