package com.retailer.task.domain.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Customized Error Response
 */
@Data
public class ErrorResponse {

    private LocalDateTime timestamp;
    private String message;
    private String details;
}
