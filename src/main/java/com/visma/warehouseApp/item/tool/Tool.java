package com.visma.warehouseApp.item.tool;

import com.visma.warehouseApp.item.Item;

import java.math.BigDecimal;

public abstract class Tool extends Item {

    public Tool() {
    }

    public Tool(int id, BigDecimal price, String name, String description, int amountInStorage) {
        super(id, price, name, description, amountInStorage);
    }
}
