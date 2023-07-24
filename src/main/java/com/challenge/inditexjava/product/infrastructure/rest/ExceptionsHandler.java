package com.challenge.inditexjava.product.infrastructure.rest;

import com.challenge.inditexjava.product.domain.MissingProductRateOnDateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionsHandler {

    @ResponseBody
    @ExceptionHandler(MissingProductRateOnDateException.class)
    protected ResponseEntity<Map<String,String>> handlePriceNotFound(MissingProductRateOnDateException ex) {

        var body = new HashMap<String, String>();
        body.put("status", HttpStatus.NOT_FOUND.value() + " " + HttpStatus.NOT_FOUND.getReasonPhrase());
        body.put("error", ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(body);

    }

}
