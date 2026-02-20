package com.ryo.ryofact.billing.dto.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FormaPago {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("nombre")
    private String nombre;
}
