package com.visma.warehouseApp.item.beverage;

import com.visma.warehouseApp.item.Item;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
@Component
public abstract class Beverage extends Item {

    private double quantity;

    public Beverage() {
    }

    public Beverage(BigDecimal price, String name, String description, double quantity) {
        super(price, name, description);
        this.quantity = quantity;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

}
