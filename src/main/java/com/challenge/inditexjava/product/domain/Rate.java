package com.challenge.inditexjava.product.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public record Rate(
        long rateId,
        int priority,
        LocalDateTime startDateTime,
        LocalDateTime endDateTime,
        MonetaryAmount price
) {
    public Rate {
        Objects.requireNonNull(startDateTime);
        Objects.requireNonNull(endDateTime);
        if (startDateTime.isAfter(endDateTime)){
            throw new IllegalArgumentException();
        }
    }

    public Boolean isDateIncluded(LocalDateTime date){
        return isAfterOrEqualStartDate(date)
                && isBeforeOrEqualEndDate(date);
    }

    private Boolean isAfterOrEqualStartDate(LocalDateTime date){
        return startDateTime.isBefore(date)
                || startDateTime.isEqual(date);
    }

    private Boolean isBeforeOrEqualEndDate(LocalDateTime date){
        return endDateTime.isAfter(date)
                || endDateTime.isEqual(date);
    }

}

