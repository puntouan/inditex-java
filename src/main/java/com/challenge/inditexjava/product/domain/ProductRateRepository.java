package com.challenge.inditexjava.product.domain;

import java.time.LocalDateTime;

public interface ProductRateRepository {

    ProductRatesOnADate getProductRatesOnDate(Long productId, Long brandId, LocalDateTime date);

}
