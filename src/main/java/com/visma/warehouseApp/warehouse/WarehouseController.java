package com.visma.warehouseApp.warehouse;

import com.visma.warehouseApp.item.Item;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("warehouse")
public class WarehouseController {

    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @GetMapping("/hello")
    public String getGreetings(){
        return "Sveiki, furistai!!";
    }

    @GetMapping("/items")
    public Set<Item> getItems(){
        return warehouseService.getItems();
    }

}
