package com.ryo.ryofact.common.exception_handler;

import com.ryo.ryofact.billing.dto.api.TaskResponseError;

public class ApiException extends RuntimeException {

    private final TaskResponseError error;

    public ApiException(TaskResponseError error) {
        super("Error en llamada externa");
        this.error = error;
    }

    public TaskResponseError getError() {
        return error;
    }
}
