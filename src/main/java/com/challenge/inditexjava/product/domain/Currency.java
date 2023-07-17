package com.challenge.inditexjava.product.domain;

public enum Currency {

    EUR("€");

    public final String symbol;

    Currency(String symbol){
        this.symbol = symbol;
    }

}
