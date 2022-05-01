package com.visma.warehouseApp.item.beverage;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
@Component
public class WaterBottle extends NonAlcoholBeverage {

    public WaterBottle() {
    }

    public WaterBottle(BigDecimal price, String name, String description, double quantity) {
        super(price, name, description, quantity);
    }


}
