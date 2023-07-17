package com.challenge.inditexjava.product.infrastructure;

import com.challenge.inditexjava.product.domain.Currency;
import com.challenge.inditexjava.product.domain.MonetaryAmount;
import com.challenge.inditexjava.product.domain.Rate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class H2PricesRowMapper implements RowMapper<Rate> {

    @Override
    public Rate mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Rate(
                rs.getLong("PRICE_LIST"),
                rs.getInt("PRIORITY"),
                rs.getTimestamp("START_DATE").toLocalDateTime(),
                rs.getTimestamp("END_DATE").toLocalDateTime(),
                new MonetaryAmount(
                    rs.getDouble("PRICE"),
                    Currency.valueOf(rs.getString("CURR"))
                )
        );
    }
}
