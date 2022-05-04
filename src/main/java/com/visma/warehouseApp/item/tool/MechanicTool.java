package com.visma.warehouseApp.item.tool;

import java.math.BigDecimal;

public class MechanicTool extends Tool{

    public MechanicTool() {
    }

    public MechanicTool(BigDecimal price, String name, String description, int amountInStorage) {
        super(price, name, description, amountInStorage);
    }
}
