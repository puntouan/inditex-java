package com.challenge.inditexjava.product.infrastructure;

import com.challenge.inditexjava.product.application.GetProductRateByDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class ProductController {

    private final GetProductRateByDate getProductRateByDate;
    private final ProductRateDtoMapper productRateDtoMapper;

    @Autowired
    public ProductController(
            GetProductRateByDate getProductRateByDate,
            ProductRateDtoMapper productRateDtoMapper
    ){
        this.getProductRateByDate = getProductRateByDate;
        this.productRateDtoMapper = productRateDtoMapper;
    }

    @GetMapping("/brand/{brandId}/product/{productId}/prices")
    public ProductRateDto getProductRateByDate(@PathVariable Long brandId, @PathVariable Long productId, @RequestParam LocalDateTime date){

        return productRateDtoMapper.mapFromDomainToDto(
                getProductRateByDate.execute(productId, brandId, date)
        );

    }

}
