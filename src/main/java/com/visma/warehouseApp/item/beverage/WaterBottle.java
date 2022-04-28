package com.visma.warehouseApp.item.beverage;

import java.math.BigDecimal;

public class WaterBottle extends NonAlcoholBeverage {

    public WaterBottle() {
    }

    public WaterBottle(BigDecimal price, String name, String description, double quantity) {
        super(price, name, description, quantity);
    }


}
