package com.ryo.ryofact.billing.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class BillingCreate {

    @JsonProperty("billing_identifier")
    @NotBlank
    private String billingIdentifier;

    @JsonProperty("reference")
    @NotBlank
    private String reference;

    @JsonProperty("total")
    @NotNull
    @DecimalMin("0.1")
    private BigDecimal total;
}
