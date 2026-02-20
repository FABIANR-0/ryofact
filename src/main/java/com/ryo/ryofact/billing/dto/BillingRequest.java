package com.ryo.ryofact.billing.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BillingRequest {

    @JsonProperty("client_identifier")
    @NotBlank
    private String clientIdentifier;

    @JsonProperty("billing_identifier")
    private String billingIdentifier;
}
