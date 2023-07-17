package com.challenge.inditexjava.product.domain;

import java.util.Objects;

public record ProductRate(
        long brandId,
        long productId,
        Rate rate
) {
    public ProductRate {
        Objects.requireNonNull(rate);
    }

}
