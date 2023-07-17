package com.challenge.inditexjava.product.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SuppressWarnings("NewClassNamingConvention")
public class ProductRatesOnADateShould {

    @Test
    void notAllowARateThatDoesNotIncludeTheApplicationDate() {

        // Given
        var applicationDate = LocalDateTime.now();

        var rateIncludingApplicationDate = RateMother.randomWithStartAndEndDates(
                applicationDate.minusDays(1),
                applicationDate.plusDays(1)
        );
        var rateNotIncludingApplicationDate = RateMother.randomWithStartAndEndDates(
                applicationDate.plusDays(1),
                applicationDate.plusDays(2)
        );

        // Then
        var exc = assertThrows(IllegalArgumentException.class, () -> {
            // When
            ProductRatesOnADateMother.randomWithRates(
                    Arrays.asList(rateIncludingApplicationDate, rateNotIncludingApplicationDate)
            );
        });
        assert(exc.getMessage().contains("at least one rate that does not include the application date"));

        // Then
        exc = assertThrows(IllegalArgumentException.class, () -> {
            // When
            ProductRatesOnADateMother.randomWithRates(
                    Arrays.asList(rateNotIncludingApplicationDate, rateIncludingApplicationDate)
            );
        });
        assert(exc.getMessage().contains("at least one rate that does not include the application date"));
    }

    @Test
    void notAllowMoreThanRateToApply() {

        // Given
        var applicationDate = LocalDateTime.now();
        var rateStartDate = applicationDate.minusDays(1);
        var rateEndDate = applicationDate.plusDays(1);

        var rateOne = RateMother.randomWithStartAndEndDatesAndPriority(
                rateStartDate, rateEndDate,1);
        var rateTwo = RateMother.randomWithStartAndEndDatesAndPriority(
                rateStartDate, rateEndDate,2);
        var rateThree = RateMother.randomWithStartAndEndDatesAndPriority(
                rateStartDate, rateEndDate,2);

        // Then
        var exc = assertThrows(IllegalArgumentException.class, () -> {
            // When
            ProductRatesOnADateMother.randomWithRates(
                    Arrays.asList(rateOne, rateTwo, rateThree)
            );
        });
        assert(exc.getMessage().contains("There is more than one rate to apply"));

    }

    @Test
    void returnAsARateToBeAppliedToTheOneWithTheHighestPriority() {
        // Given
        var applicationDate = LocalDateTime.now();
        var rateStartDate = applicationDate.minusDays(1);
        var rateEndDate = applicationDate.plusDays(1);

        var rateOne = RateMother.randomWithStartAndEndDatesAndPriority(
                rateStartDate, rateEndDate,1);
        var rateTwo = RateMother.randomWithStartAndEndDatesAndPriority(
                rateStartDate, rateEndDate,2);
        var rateWithMaxPriority = RateMother.randomWithStartAndEndDatesAndPriority(
                rateStartDate, rateEndDate,5);

        var productRatesOnADate = ProductRatesOnADateMother.randomWithRates(
                Arrays.asList(rateOne, rateWithMaxPriority, rateTwo)
        );

        // When
        var rateToApply = productRatesOnADate.getRateToApply();

        // Then
        assert rateToApply != null;
        assertEquals(productRatesOnADate.productId(), rateToApply.productId());
        assertEquals(productRatesOnADate.brandId(), rateToApply.brandId());
        assertEquals(rateWithMaxPriority, rateToApply.rate());
    }

}
