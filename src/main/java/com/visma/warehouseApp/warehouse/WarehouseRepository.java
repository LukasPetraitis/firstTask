package com.visma.warehouseApp.warehouse;

import com.visma.warehouseApp.item.Item;
import com.visma.warehouseApp.item.beverage.WaterBottle;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Repository
@Scope("singleton")
public class WarehouseRepository implements WarehouseDAO{

    @Override
    public Set<Item> getItems() {
        return Stream.of(
                new WaterBottle(new BigDecimal("0.98"), "Neptunas", "Neptuno Vanduo", 0.5))
                    .collect(Collectors.toSet());
    }
}
