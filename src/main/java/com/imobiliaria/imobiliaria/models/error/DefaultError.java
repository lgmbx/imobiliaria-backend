package com.imobiliaria.imobiliaria.models.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@AllArgsConstructor
public class DefaultError {

    @JsonProperty("timestamp")
    private final LocalDateTime timestamp;

    @JsonProperty("status code")
    private final Integer status;

    @JsonProperty("status error")
    private final String error;

    @JsonProperty("error messages")
    private final Map<String, String> messages;

    @JsonProperty("path")
    private final String path;

}
