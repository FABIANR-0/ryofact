package com.ryo.ryofact.billing.dto.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class TaskResponse {

    @JsonProperty("task_id")
    private String taskId;

    @JsonProperty("messages")
    private List<String> messages;
}
