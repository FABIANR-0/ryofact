package com.ryo.ryofact.billing.dto.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Cliente {

    @JsonProperty("usuario")
    private String usuario;

    @JsonProperty("nombre")
    private String nombre;

    @JsonProperty("email")
    private String email;

    @JsonProperty("cedula")
    private String cedula;

    @JsonProperty("direccion")
    private String direccion;

    @JsonProperty("localidad")
    private String localidad;

    @JsonProperty("telefono")
    private String telefono;

    @JsonProperty("rfc")
    private String rfc;
}
