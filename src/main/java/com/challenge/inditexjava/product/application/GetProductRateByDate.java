package com.challenge.inditexjava.product.application;

import com.challenge.inditexjava.product.domain.IGetProductRateByDate;
import com.challenge.inditexjava.product.domain.MissingProductRateOnDateException;
import com.challenge.inditexjava.product.domain.ProductRate;
import com.challenge.inditexjava.product.domain.ProductRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class GetProductRateByDate implements IGetProductRateByDate {
    private final ProductRateRepository productRateRepository;

    @Autowired
    public GetProductRateByDate(ProductRateRepository productRateRepository){
        this.productRateRepository = productRateRepository;
    }

    public ProductRate execute(Long productId, Long brandId, LocalDateTime date){

        var productRatesOnADate = productRateRepository.getProductRatesOnDate(productId, brandId, date);

        if (productRatesOnADate == null){
            throw new MissingProductRateOnDateException(productId, brandId, date);
        }

        var rateToApply = productRatesOnADate.getRateToApply();
        if (rateToApply == null){
            throw new MissingProductRateOnDateException(productId, brandId, date);
        }

        return rateToApply;

    }

}
