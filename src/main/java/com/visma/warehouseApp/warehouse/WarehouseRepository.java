package com.visma.warehouseApp.warehouse;

import com.visma.warehouseApp.item.Item;
import com.visma.warehouseApp.item.beverage.WaterBottle;
import com.visma.warehouseApp.item.tool.MechanicTool;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Repository
@Scope("singleton")
public class WarehouseRepository implements WarehouseDAO{

    static{
        items = Stream.of(
                        new WaterBottle(1, new BigDecimal("0.98"), "Water", "drinkable water", 15 ,0.5),
                        new MechanicTool(2,new BigDecimal("9.90"), "Hammer", "good old hammer", 20),
                        new MechanicTool(3, new BigDecimal("4.95"), "Screwdriver", "eletrician screwdriver", 5),
                        new MechanicTool(4, new BigDecimal("15.99"), "Hand saw", "timber saw", 5)
                )
                .collect(Collectors.toSet());
    }

    private static Set<Item> items;

    @Override
    public Set<Item> getItems() {
        return items;
    }

    @Override
    public void save(MechanicTool mechanicTool) {
        items.add(mechanicTool);
    }

    public void updateAmount(Item item) {
        items.stream()
                .filter(x -> x
                .getId() == item.getId())
                .findFirst().get().setAmountInStorage(item.getAmountInStorage() - 1);
    }
}
