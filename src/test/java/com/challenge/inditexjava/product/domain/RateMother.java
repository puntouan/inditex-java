package com.challenge.inditexjava.product.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RateMother {

    private static final Random rnd = new Random(System.currentTimeMillis());

    public static Rate randomWithStartAndEndDates(LocalDateTime startDateTime, LocalDateTime endDateTime){
        return randomWithStartAndEndDatesAndPriority(
                startDateTime,
                endDateTime,
                rnd.nextInt(0, 1000)
        );
    }

    public static Rate randomWithStartAndEndDatesAndPriority(LocalDateTime startDateTime, LocalDateTime endDateTime, int priority){
        return new Rate(
                rnd.nextLong(0, Long.MAX_VALUE),
                priority,
                startDateTime,
                endDateTime,
                new MonetaryAmount(
                        rnd.nextDouble(0.0, 10000.0),
                        Currency.EUR
                )
        );
    }

    public static List<Rate> nRandomWithApplicationDate(int n, LocalDateTime applicationDate){
        long secondsInTenYears = 365 * 24 * 60 * 60;

        var list = new ArrayList<Rate>();
        for(int i = 0; i < n; i++){
            list.add(
                    randomWithStartAndEndDates(
                            applicationDate.minusSeconds(rnd.nextLong(0, secondsInTenYears)),
                            applicationDate.plusSeconds(rnd.nextLong(0, secondsInTenYears))
                    )
            );
        }
        return list;
    }

}
