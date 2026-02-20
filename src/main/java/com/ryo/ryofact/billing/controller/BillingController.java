package com.ryo.ryofact.billing.controller;

import com.ryo.ryofact.billing.dto.BillingCreate;
import com.ryo.ryofact.billing.dto.BillingRequest;
import com.ryo.ryofact.billing.dto.api.Factura;
import com.ryo.ryofact.billing.service.BillingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/billing")
@AllArgsConstructor
public class BillingController {

    private final BillingService billingService;

    @PostMapping("/obtain-invoice")
    @Operation(description = "get all invoices of client by client identifier or billing identifier")
    @ApiResponse(responseCode = "200", description = "success")
    public ResponseEntity<List<Factura>> obtainInvoice(@RequestBody @Valid BillingRequest request) {
        List<Factura> responses = billingService.obtainInvoice(request);
        return ResponseEntity.ok(responses);
    }

    @PostMapping("/save-payment")
    @Operation(description = "save payment of client by crediservir")
    @ApiResponse(responseCode = "201", description = "created")
    public ResponseEntity<?> savePayment(@RequestBody @Valid BillingCreate request) {
        String response = billingService.savePayment(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
