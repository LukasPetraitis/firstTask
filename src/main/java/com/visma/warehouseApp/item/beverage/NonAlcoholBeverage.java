package com.visma.warehouseApp.item.beverage;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
@Component
public abstract class NonAlcoholBeverage extends Beverage{

    public NonAlcoholBeverage() {
    }

    public NonAlcoholBeverage(BigDecimal price, String name, String description, int amountInStorage, double quantity) {
        super(price, name, description, amountInStorage, quantity);
    }
}
