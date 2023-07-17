package com.challenge.inditexjava.product.application;

import com.challenge.inditexjava.product.domain.MissingProductRateOnDateException;
import com.challenge.inditexjava.product.domain.ProductRateRepository;
import com.challenge.inditexjava.product.domain.ProductRatesOnADate;
import com.challenge.inditexjava.product.domain.RateMother;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SuppressWarnings("NewClassNamingConvention")
public class GetProductRateByDateShould {

    private final ProductRateRepository productRateRepository = Mockito.mock(ProductRateRepository.class);
    private final GetProductRateByDate getProductRateByDate = new GetProductRateByDate(productRateRepository);

    private final long brandId = 1;
    private final long productId = 1;
    private final LocalDateTime applicationDate = LocalDateTime.now();

    @Test
    void returnTheHighestPriorityRateIfItExists(){

        // Given
        var rateToApply = RateMother.randomWithStartAndEndDatesAndPriority(
                applicationDate.minusDays(1), applicationDate.plusDays(1),Integer.MAX_VALUE);

        var rates = RateMother.nRandomWithApplicationDate(3, applicationDate);
        rates.add(rateToApply);

        var productRatesOnADate = new ProductRatesOnADate(
                brandId, productId, applicationDate, rates);

        when(productRateRepository.getProductRatesOnDate(productId, brandId, applicationDate))
                .thenReturn(productRatesOnADate);

        // When
        var returnedProductRate = getProductRateByDate.execute(productId, brandId, applicationDate);

        // Then
        assertEquals(rateToApply, returnedProductRate.rate());
        assertEquals(brandId, returnedProductRate.brandId());
        assertEquals(productId, returnedProductRate.productId());
    }

    @Test
    void failIfThereIsNoPriorityRate(){

        // Given
        var productRatesOnADate = new ProductRatesOnADate(
                brandId, productId, applicationDate, new ArrayList<>());

        when(productRateRepository.getProductRatesOnDate(productId, brandId, applicationDate))
                .thenReturn(productRatesOnADate);

        // Then
        var exc = assertThrows(MissingProductRateOnDateException.class, () -> {
            // When
            getProductRateByDate.execute(productId, brandId, applicationDate);
        });
        assertTrue(exc.getMessage().contains("There is no rate for product"));


    }


}