package com.ryo.ryofact.billing.dto.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Articulo {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("uuid_equipo")
    private String uuidEquipo;

    @JsonProperty("categoria_stock")
    private String categoriaStock;

    @JsonProperty("cantidad")
    private Integer cantidad;

    @JsonProperty("descripcion")
    private String descripcion;

    @JsonProperty("precio")
    private String precio;

    @JsonProperty("servicio")
    private Servicio servicio;
}