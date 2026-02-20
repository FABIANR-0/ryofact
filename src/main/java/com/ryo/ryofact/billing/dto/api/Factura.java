package com.ryo.ryofact.billing.dto.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Factura {

    @JsonProperty("id_factura")
    private Long idFactura;

    @JsonProperty("folio")
    private String folio;

    @JsonProperty("fecha_emision")
    private String fechaEmision;

    @JsonProperty("fecha_vencimiento")
    private String fechaVencimiento;

    @JsonProperty("fecha_pago")
    private String fechaPago;

    @JsonProperty("estado")
    private String estado;

    @JsonProperty("tipo")
    private Integer tipo;

    @JsonProperty("zona")
    private Zona zona;

    @JsonProperty("sub_total")
    private Double subTotal;

    @JsonProperty("descuento")
    private Double descuento;

    @JsonProperty("saldo")
    private Double saldo;

    @JsonProperty("saldo_nuevo")
    private Double saldoNuevo;

    @JsonProperty("impuestos_total")
    private Double impuestosTotal;

    @JsonProperty("total_cobrado")
    private Double totalCobrado;

    @JsonProperty("total")
    private Double total;

    @JsonProperty("comprobante_pago")
    private String comprobantePago;

    @JsonProperty("referencia")
    private String referencia;

    @JsonProperty("referencia_oxxo")
    private String referenciaOxxo;

    @JsonProperty("total_oxxo")
    private Double totalOxxo;

    @JsonProperty("id_mercadopago")
    private String idMercadopago;

    @JsonProperty("id_payu")
    private String idPayu;

    @JsonProperty("url_payu")
    private String urlPayu;

    @JsonProperty("total_pasarela")
    private Double totalPasarela;

    @JsonProperty("total_openpay")
    private Double totalOpenpay;

    @JsonProperty("retencion_porcentaje")
    private Double retencionPorcentaje;

    @JsonProperty("retenciones_total")
    private Double retencionesTotal;

    @JsonProperty("forma_pago")
    private FormaPago formaPago;

    @JsonProperty("cajero")
    private Cajero cajero;

    @JsonProperty("cliente")
    private Cliente cliente;

    @JsonProperty("articulos")
    private List<Articulo> articulos;
}
