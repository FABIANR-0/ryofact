package com.ryo.ryofact.common.util.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ryo.ryofact.billing.dto.api.TaskResponse;
import com.ryo.ryofact.common.exception_handler.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@Slf4j
public class HttpUtil {

    private static final String MESSAGE = "Estamos teniendo problemas, reinténtalo más tarde.";

    public static HttpResponse<String> httpRequestPost(String url, String jsonRequest, String valueAuthorization) {
        try {
            HttpRequest httpRequest = HttpRequest
                    .newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .header("Authorization", valueAuthorization)
                    .POST(HttpRequest.BodyPublishers.ofString(jsonRequest))
                    .timeout(Duration.ofSeconds(20))
                    .build();


            HttpClient httpClient = HttpClient.newBuilder().followRedirects(HttpClient.Redirect.ALWAYS).build();
            return httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        } catch (Exception ex) {
            log.error("Error en la petición POST {}", ex.getMessage());
            throw new ResourceNotFoundException(MESSAGE);
        }
    }

    public static HttpResponse<String> httpRequestGet(String url, String accessToken) {
        try {
            HttpRequest.Builder requestBuilder = HttpRequest
                    .newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .timeout(Duration.ofSeconds(10));
            if (accessToken != null) {
                requestBuilder.header("Authorization", accessToken);
            }
            HttpClient httpClient = HttpClient.newBuilder().followRedirects(HttpClient.Redirect.ALWAYS).build();
            return httpClient.send(requestBuilder.GET().build(), HttpResponse.BodyHandlers.ofString());

        } catch (Exception ex) {
            log.error("Error en la petición GET ", ex);
            throw new ResourceNotFoundException(MESSAGE);
        }
    }

    public static Object  requestPost(String url, String jsonRequest, String valueAuthorization) {
        HttpResponse<String> response = httpRequestPost(url, jsonRequest, valueAuthorization);

        if (response.statusCode() != 200 && response.statusCode() != 201 ) {
            log.error("Error en la petición POST: Código de estado {}", response.statusCode());
            return response.body();
        }

        try {
            return new ObjectMapper().readValue(response.body(), TaskResponse.class);
        } catch (Exception ex) {
            log.error("Error al deserializar el JSON de respuesta petición POST.");
            throw new IllegalArgumentException("Error obteniendo respuesta");
        }
    }

    public static <T> T requestGet(String url, String accessToken, Class<T> responseType) {
        HttpResponse<String> response = httpRequestGet(url, accessToken);

        if (response.statusCode() != 200) {
            log.error("Error en la petición GET: Código de estado {}", response.statusCode());
            throw new ResourceNotFoundException("No se pudo obtener el recurso.");
        }

        try {
            return new ObjectMapper().readValue(response.body(), responseType);
        } catch (Exception ex) {
            log.error("Error al deserializar el JSON de respuesta petición GET.");
            throw new IllegalArgumentException("Error obteniendo respuesta");
        }
    }
}
