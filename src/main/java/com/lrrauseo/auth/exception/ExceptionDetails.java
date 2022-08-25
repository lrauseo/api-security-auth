package com.lrrauseo.auth.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode
public class ExceptionDetails {
    private LocalDateTime date;
    private String title;
    private String message;
    private int statusCode;
    private HttpStatus status;
    private String developerMessage;
}
