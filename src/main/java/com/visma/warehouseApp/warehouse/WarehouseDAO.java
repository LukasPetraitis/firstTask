package com.visma.warehouseApp.warehouse;

import com.visma.warehouseApp.item.Item;

import java.util.Set;

public interface WarehouseDAO {
    public Set<Item> getItems();
}
