package com.equinte.gotur.exceptionHandlers;

import com.equinte.gotur.exceptions.GeneralException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Component
@ControllerAdvice
public class GeneralExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GeneralExceptionHandler.class);

    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<String> generalExceptionHandler(GeneralException e) {
        ResponseEntity<String> response = new ResponseEntity<>(e.getResponseMessage(), e.getStatus());
        log.error("General Exception occurred. message:{}", e.getLogMessage());

        return response;
    }
}
