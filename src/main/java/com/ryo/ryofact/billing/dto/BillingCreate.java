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
    @NotBlank(message = "el identificador de la factura no puede estar vacío o nulo")
    private String billIdentifier;

    @JsonProperty("reference")
    @NotBlank(message = "la referencia de la factura no puede estar vacía o nula")
    private String reference;

    @JsonProperty("total")
    @NotNull(message = "el total a pagar de la factura no puede ser nulo")
    @DecimalMin(value = "0.1", message = "el total a pagar de la factura debe ser mayor a 0.1")
    private BigDecimal total;
}
