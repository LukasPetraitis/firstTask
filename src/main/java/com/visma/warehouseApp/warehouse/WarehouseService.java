package com.visma.warehouseApp.warehouse;

import com.visma.warehouseApp.item.Item;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Scope("singleton")
public class WarehouseService {

    private final WarehouseDAO warehouseDAO;

    public WarehouseService(WarehouseDAO warehouseDAO) {
        this.warehouseDAO = warehouseDAO;
    }

    public Set<Item> getItems(){
        return warehouseDAO.getItems();
    }
}
