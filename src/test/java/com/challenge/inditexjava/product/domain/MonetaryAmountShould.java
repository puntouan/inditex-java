package com.challenge.inditexjava.product.domain;

import org.junit.jupiter.api.Test;

import static com.challenge.inditexjava.product.domain.Currency.EUR;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("NewClassNamingConvention")
public class MonetaryAmountShould {

    @Test
    void correctlyRoundUpPositiveNumbers(){
        // Given
        var original = new MonetaryAmount(123.456789, EUR);

        // When
        var rounded = original.round();

        // Then
        assertEquals(123.46, rounded.amount());
        assertEquals(original.currency(), rounded.currency());
    }

    @Test
    void correctlyRoundDownPositiveNumbers(){
        // Given
        var original = new MonetaryAmount(123.4512345, EUR);

        // When
        var rounded = original.round();

        // Then
        assertEquals(123.45, rounded.amount());
        assertEquals(original.currency(), rounded.currency());
    }

    @Test
    void correctlyRoundDownNegativeNumbers(){
        // Given
        var original = new MonetaryAmount(-123.456789, EUR);

        // When
        var rounded = original.round();

        // Then
        assertEquals(-123.46, rounded.amount());
        assertEquals(original.currency(), rounded.currency());
    }

    @Test
    void correctlyRoundUpNegativeNumbers(){
        // Given
        var original = new MonetaryAmount(-123.4512345, EUR);

        // When
        var rounded = original.round();

        // Then
        assertEquals(-123.45, rounded.amount());
        assertEquals(original.currency(), rounded.currency());
    }
}