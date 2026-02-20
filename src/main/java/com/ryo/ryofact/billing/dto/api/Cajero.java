package com.ryo.ryofact.billing.dto.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Cajero {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("nombre")
    private String nombre;
}