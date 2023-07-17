package com.challenge.inditexjava.product.infrastructure;

import com.challenge.inditexjava.product.domain.ProductRate;
import org.springframework.stereotype.Component;

@Component
public class ProductRateDtoMapper {
    ProductRateDto mapFromDomainToDto(ProductRate productRate){

        var rounded = productRate.rate().price().round();

        return new ProductRateDto(
                productRate.brandId(),
                productRate.productId(),
                productRate.rate().rateId(),
                productRate.rate().startDateTime(),
                productRate.rate().endDateTime(),
                rounded.amount() + rounded.currency().symbol
        );
    }

}
