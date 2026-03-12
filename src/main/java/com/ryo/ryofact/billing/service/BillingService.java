package com.ryo.ryofact.billing.service;

import com.ryo.ryofact.billing.dto.BillingCreate;
import com.ryo.ryofact.billing.dto.BillingRequest;
import com.ryo.ryofact.billing.dto.api.Factura;
import com.ryo.ryofact.billing.dto.api.FacturaResponse;
import com.ryo.ryofact.billing.dto.api.TaskResponse;
import com.ryo.ryofact.billing.entity.Billing;
import com.ryo.ryofact.billing.repository.BillingRepository;
import com.ryo.ryofact.common.exception_handler.ConflictException;
import com.ryo.ryofact.common.exception_handler.ResourceNotFoundException;
import com.ryo.ryofact.common.util.http.HttpUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BillingService {

    @Value("${settings.api-key}")
    private String apiKey;

    @Value("${settings.url-base}")
    private String urlBase;

    private final BillingRepository billingRepository;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public List<Factura> obtainInvoice(BillingRequest request) {
        if (request.getBillingIdentifier() != null && !request.getBillingIdentifier().isEmpty()) {
            return obtainInvoiceByBillingIdentifier(request);
        }
        LocalDate now = LocalDate.now().plusDays(1);
        boolean findResults = Boolean.FALSE;
        List<Factura> pendingBillings = new ArrayList<>();
        String urlFinal = urlBase +
                "/api/facturas/?fecha_emision__range_0=" +
                now.minusMonths(1).withDayOfMonth(1) +
                "&fecha_emision__range_1=" +
                now +
                "&estado=1";

        while (!findResults) {

            FacturaResponse billingResponse = HttpUtil.requestGet(urlFinal, apiKey, FacturaResponse.class);

            List<Factura> foundBillingByClient = billingResponse.getResults()
                    .stream()
                    .filter(factura -> factura.getCliente() != null)
                    .filter(factura -> request.getClientIdentifier().equals(factura.getCliente().getCedula()))
                    .toList();

            pendingBillings.addAll(foundBillingByClient);

            urlFinal = billingResponse.getNext() != null ? billingResponse.getNext().replace("http", "https") : "";
            findResults = billingResponse.getNext() == null || pendingBillings.size() >= 2;

        }

        if (pendingBillings.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron facturas pendientes para el cliente con identificador: " + request.getClientIdentifier());
        }

        return pendingBillings;
    }

    private List<Factura> obtainInvoiceByBillingIdentifier(BillingRequest request) {
        String urlFinal = urlBase + "/api/facturas/" + request.getBillingIdentifier();
        Factura billingResponse = HttpUtil.requestGet(urlFinal, apiKey, Factura.class);

        if (billingResponse == null) {
            throw new ResourceNotFoundException("No se encontró la factura con el identificador: " + request.getBillingIdentifier());
        }
        return List.of(billingResponse);
    }


    public String savePayment(BillingCreate request) {
        if (billingRepository.existsBillingByBillingIdentifierWisphubAndIsSaveWisphubIsTrue(request.getBillingIdentifier()))
            throw new ConflictException(String.format("La factura con identificador %s ya tiene registrado un pago", request.getBillingIdentifier()));

        String urlFinal = urlBase + "/api/facturas/" + request.getBillingIdentifier() + "/registrar-pago/";
        String reference = "CDR_" + request.getReference() + "_MRO";

        String formattedDate = LocalDateTime.now().format(FORMATTER);

        String jsonBody = "{"
                + "\"referencia\": \"" + reference + "\","
                + "\"fecha_pago\": \"" + formattedDate + "\","
                + "\"total_cobrado\":" + request.getTotal()
                + ",\"accion\": 1"
                + ",\"forma_pago\": 47091"
                + "}";

        int times = 0;

        while (times < 3) {
            Object responseGeneral = HttpUtil.requestPost(urlFinal, jsonBody, apiKey);
            Billing billing;
            if (responseGeneral instanceof TaskResponse response) {
                 billing = Billing.create(
                        reference,
                        request.getTotal(),
                        request.getBillingIdentifier(),
                        true,
                        response.toString()
                );
                times = 3;
            } else {
                times++;
                 billing = Billing.create(
                        reference,
                        request.getTotal(),
                        request.getBillingIdentifier(),
                        false,
                        responseGeneral.toString()
                );
            }
            billingRepository.save(billing);
        }

        return String.format("registro de pago para la referencia %s fue completado", request.getReference());

    }
}