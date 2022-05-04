package com.visma.warehouseApp.warehouse;

import com.visma.warehouseApp.item.Item;
import com.visma.warehouseApp.item.beverage.WaterBottle;
import com.visma.warehouseApp.item.tool.MechanicTool;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.*;


@Repository
@Scope("singleton")
public class WarehouseRepository implements WarehouseDAO{

    @PostConstruct
    private void postConstruct(){
        items.put(1, new WaterBottle(new BigDecimal("0.98"), "Water", "drinkable water", 15 ,0.5));
        items.put(2, new MechanicTool(new BigDecimal("9.90"), "Hammer", "good old hammer", 20));
        items.put(3, new MechanicTool(new BigDecimal("4.95"), "Screwdriver", "eletrician screwdriver", 5));
        items.put(4, new MechanicTool(new BigDecimal("15.99"), "Hand saw", "timber saw", 5));
    }


    private static Map<Integer, Item> items;
    private static Integer id;

    public WarehouseRepository() {
        items = new HashMap<Integer, Item>();
        id = 6;
    }

    @Override
    public Map<Integer, Item> getItems() {
        return items;
    }

    @Override
    public void save(Item item) {
        items.put( id++, item);
    }

    public void updateAmount(int id) {
        int inStorage = items.get(id).getAmountInStorage();
        items.get(id).setAmountInStorage(inStorage - 1);
    }

    public Optional<Item> getItemById(int id) {
        Optional<Item> itemById = Optional.ofNullable(getItems().get(id));
        return itemById;
    }
}
