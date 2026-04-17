package com.ryo.ryofact.billing.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BillingRequest {

    @JsonProperty("client_identifier")
    @NotBlank(message = "el identificador del cliente no puede estar vacío o nulo")
    private String clientIdentifier;

    @JsonProperty("bill_identifier")
    private String billIdentifier;
}
