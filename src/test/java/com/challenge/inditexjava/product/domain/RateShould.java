package com.challenge.inditexjava.product.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SuppressWarnings("NewClassNamingConvention")
public class RateShould {

    @Test
    void notAllowAStartDateAfterTheEndDate() {

        // Given
        var startDate = LocalDateTime.now();
        var endDate = startDate.minusDays(1);

        // Then
        assertThrows(IllegalArgumentException.class, () -> {
            // When
            RateMother.randomWithStartAndEndDates(
                    startDate, endDate
            );
        });

    }

    @Test
    void returnThatTheDateIsNotIncludedWhenItIsBeforeTheStartDate() {
        // Given
        var rate = RateMother.randomWithStartAndEndDates(
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(2)
        );

        // When
        var isDateIncluded = rate.isDateIncluded( rate.startDateTime().minusDays(1));

        // Then
        assertFalse(isDateIncluded);
    }

    @Test
    void returnThatTheDateIsIncludedWhenItIsEqualToTheStartDate() {
        // Given
        var rate = RateMother.randomWithStartAndEndDates(
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(2)
        );

        // When
        var isDateIncluded = rate.isDateIncluded( rate.startDateTime());

        // Then
        assertTrue(isDateIncluded);
    }

    @Test
    void returnThatTheDateIsIncludedWhenItIsAfterTheStartDateAndBeforeTheEndDate() {
        // Given
        var rate = RateMother.randomWithStartAndEndDates(
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(2)
        );

        // When
        var isDateIncluded = rate.isDateIncluded( rate.startDateTime().plusDays(1));

        // Then
        assertTrue(isDateIncluded);
    }

    @Test
    void returnThatTheDateIsIncludedWhenItIsEqualToTheEndDate() {
        // Given
        var rate = RateMother.randomWithStartAndEndDates(
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(2)
        );

        // When
        var isDateIncluded = rate.isDateIncluded( rate.endDateTime());

        // Then
        assertTrue(isDateIncluded);
    }

    @Test
    void returnThatTheDateIsNotIncludedWhenItIsAfterTheEndDate() {
        // Given
        var rate = RateMother.randomWithStartAndEndDates(
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(2)
        );

        // When
        var isDateIncluded = rate.isDateIncluded( rate.endDateTime().plusDays(1));

        // Then
        assertFalse(isDateIncluded);
    }

}
