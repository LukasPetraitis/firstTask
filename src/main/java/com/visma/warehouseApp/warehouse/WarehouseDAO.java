package com.visma.warehouseApp.warehouse;

import com.visma.warehouseApp.item.Item;
import com.visma.warehouseApp.item.tool.MechanicTool;

import java.util.Set;

public interface WarehouseDAO {
    public Set<Item> getItems();

    public void save(MechanicTool mechanicTool);
}
