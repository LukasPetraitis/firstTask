package com.visma.warehouseApp.warehouse;

import com.visma.warehouseApp.item.Item;
import com.visma.warehouseApp.item.tool.MechanicTool;

import java.util.Map;
import java.util.Set;

public interface WarehouseDAO {
    public Map<Integer, Item> getItems();

    public void save(Item item);
}
