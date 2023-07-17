package com.challenge.inditexjava.product.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

public class ProductRatesOnADateMother {

    private static final Random rnd = new Random(System.currentTimeMillis());

    public static ProductRatesOnADate randomWithRates(List<Rate> rates){
        return new ProductRatesOnADate(
                rnd.nextLong(0, Long.MAX_VALUE),
                rnd.nextLong(0, Long.MAX_VALUE),
                LocalDateTime.now(),
                rates
        );
    }

}
