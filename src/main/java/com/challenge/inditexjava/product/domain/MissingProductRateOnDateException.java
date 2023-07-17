package com.challenge.inditexjava.product.domain;

import java.time.LocalDateTime;

public class MissingProductRateOnDateException extends RuntimeException{

    public MissingProductRateOnDateException(Long productId, Long brandId, LocalDateTime date){
        super("There is no rate for product " + productId + " and brand " + brandId  + " for the date " + date);
    }

}
