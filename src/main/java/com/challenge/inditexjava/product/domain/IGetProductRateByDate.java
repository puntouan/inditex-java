package com.challenge.inditexjava.product.domain;

import java.time.LocalDateTime;

public interface IGetProductRateByDate {

    ProductRate execute(Long productId, Long brandId, LocalDateTime date);

}
