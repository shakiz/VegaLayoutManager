package com.stone.vega;

/**
 * Created by xmuSistone on 2017/9/20.
 */

public class StockEntity {

    private String name;
    private float price;
    private String gross;

    public StockEntity(String name, float price, String gross) {
        this.name = name;
        this.price = price;
        this.gross = gross;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public String getGross() {
        return gross;
    }
}
