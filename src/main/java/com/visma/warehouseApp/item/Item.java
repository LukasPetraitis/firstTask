package com.visma.warehouseApp.item;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
@Component
public abstract class Item {
    private BigDecimal price;
    private String name;
    private String description;

    public Item() {
    }

    public Item(BigDecimal price, String name, String description) {
        this.price = price;
        this.name = name;
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
