package com.equinte.gotur.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class GeneralException extends RuntimeException {
    private final String logMessage;
    private final String responseMessage;
    private final HttpStatus status;
}
