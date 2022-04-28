package com.visma.warehouseApp.item.beverage;

import java.math.BigDecimal;

public abstract class NonAlcoholBeverage extends Beverage{

    public NonAlcoholBeverage() {
    }

    public NonAlcoholBeverage(BigDecimal price, String name, String description, double quantity) {
        super(price, name, description, quantity);
    }
}
