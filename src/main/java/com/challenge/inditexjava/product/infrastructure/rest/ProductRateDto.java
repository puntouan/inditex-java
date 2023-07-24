package com.challenge.inditexjava.product.infrastructure.rest;

import java.time.LocalDateTime;

public record ProductRateDto(
        long brandId,
        long productId,
        Long rateId,
        LocalDateTime startDateTime,
        LocalDateTime endDateTime,
        String price
){

}
