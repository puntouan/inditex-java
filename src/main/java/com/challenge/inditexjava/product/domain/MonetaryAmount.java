package com.challenge.inditexjava.product.domain;

import java.util.Objects;

public record MonetaryAmount(
        double amount,
        Currency currency
) {
    public MonetaryAmount {
        Objects.requireNonNull(currency);
    }

    private Double roundAmount(int decimals){
        var multiplier = 1.0;
        for (int i=0; i<decimals;i++) {
            multiplier *= 10;
        }
        return Math.round(amount * multiplier) / multiplier;
    }

    public MonetaryAmount round(int decimals){
        return new MonetaryAmount(roundAmount(decimals), currency);
    }
    public MonetaryAmount round(){
        return round(2);
    }

}
