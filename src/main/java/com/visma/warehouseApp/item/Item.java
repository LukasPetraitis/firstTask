package com.visma.warehouseApp.item;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
@Component
public abstract class Item {

    private int id;
    private BigDecimal price;
    private String name;
    private String description;
    private int amountInStorage;

    public Item() {
    }

    public Item(int id,BigDecimal price, String name, String description, int amountInStorage) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.description = description;
        this.amountInStorage = amountInStorage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmountInStorage() {
        return amountInStorage;
    }

    public void setAmountInStorage(int amountInStorage) {
        this.amountInStorage = amountInStorage;
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
