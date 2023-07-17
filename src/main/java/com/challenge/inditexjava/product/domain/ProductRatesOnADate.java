package com.challenge.inditexjava.product.domain;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public record ProductRatesOnADate(
        long brandId,
        long productId,
        LocalDateTime applicationDate,
        List<Rate> rates
) {

    private static Rate rateWithMaxPriority(List<Rate> rates){
        return rates.stream()
                .max(Comparator.comparing(Rate::priority))
                .orElse(null);
    }
    public ProductRatesOnADate {
        Objects.requireNonNull(applicationDate);
        Objects.requireNonNull(rates);

        if (!rates.stream().allMatch(r -> r.isDateIncluded(applicationDate))){
            throw new IllegalArgumentException("There is at least one rate that does not include the application date");
        }

        var rateWithMaxPriority = rateWithMaxPriority(rates);
        if (rateWithMaxPriority != null){
            if ( rates.stream()
                    .filter( r -> r.priority() == rateWithMaxPriority.priority())
                    .count() > 1){
                throw new IllegalArgumentException("There is more than one rate to apply");
            }
        }
   }

   public ProductRate getRateToApply(){
       var rateWithMaxPriority = rateWithMaxPriority(this.rates);
       if (rateWithMaxPriority == null) return null;
       return new ProductRate(brandId, productId, rateWithMaxPriority);
   }

}
