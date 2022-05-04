package com.visma.warehouseApp.item.tool;

import com.visma.warehouseApp.item.Item;

import java.math.BigDecimal;

public abstract class Tool extends Item {

    public Tool() {
    }

    public Tool(BigDecimal price, String name, String description, int amountInStorage) {
        super(price, name, description, amountInStorage);
    }
}
