package com.challenge.inditexjava.product.infrastructure;

import com.challenge.inditexjava.product.domain.ProductRateRepository;
import com.challenge.inditexjava.product.domain.ProductRatesOnADate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class H2ProductRateRepository implements ProductRateRepository {

    private final JdbcTemplate template;
    private final H2PricesRowMapper pricesRowMapper = new H2PricesRowMapper();

    public H2ProductRateRepository(JdbcTemplate template){
        this.template = template;
    }

    @Override
    public ProductRatesOnADate getProductRatesOnDate(Long productId, Long brandId, LocalDateTime date) {

        String query = """
                SELECT * FROM PRICES
                WHERE
                    PRODUCT_ID = ? AND BRAND_ID = ? AND
                    START_DATE <= ? AND END_DATE >= ?              \s
                """;

        var rates = template.query(
                query,pricesRowMapper, productId, brandId, date, date
        );

        if (rates.isEmpty()) return null;

        return new ProductRatesOnADate(
                brandId, productId, date, rates
        );

    }

}
