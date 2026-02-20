package com.ryo.ryofact.billing.dto.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Servicio {

    @JsonProperty("id_servicio")
    private Long idServicio;

}