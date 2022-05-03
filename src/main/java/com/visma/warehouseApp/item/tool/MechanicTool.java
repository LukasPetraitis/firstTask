package com.visma.warehouseApp.item.tool;

import java.math.BigDecimal;

public class MechanicTool extends Tool{

    public MechanicTool() {
    }

    public MechanicTool(int id, BigDecimal price, String name, String description, int amountInStorage) {
        super(id, price, name, description, amountInStorage);
    }
}
