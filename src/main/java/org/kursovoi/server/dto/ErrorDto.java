package org.kursovoi.server.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ErrorDto {

    private long statusCode;
    private String message;
    private LocalDateTime timestamp;
}
