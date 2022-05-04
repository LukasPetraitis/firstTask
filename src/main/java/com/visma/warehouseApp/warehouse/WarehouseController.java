package com.visma.warehouseApp.warehouse;

import com.visma.warehouseApp.item.Item;

import com.visma.warehouseApp.item.ItemDTO;

import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("warehouse")
public class WarehouseController {

    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @GetMapping("/hello")
    public String getGreetings(){
        return "hello this is warehouse!!";
    }

    @GetMapping("/items")
    public Map<Integer, Item> getItems(){
        return warehouseService.getItems();
    }

    @GetMapping("/items/{id}")
    public Optional<Item> getItem(@PathVariable int id){
        return warehouseService.getItemById(id);
    }

    @PostMapping("/newItem")
    public String createMechanicalTool(@RequestBody ItemDTO itemDTO) {

        if(itemDTO.equals(null)){
            return "error";
        }
        else if(itemDTO.getItemType().equals("tool")){
            warehouseService.saveTool(itemDTO);
        }
        else if(itemDTO.getItemType().equals("beverage")){
            warehouseService.saveBeverage(itemDTO);
        }
        return "item saved";
    }

    @GetMapping("/Items/{id}/sell")
    public String sellItem(@PathVariable int id){
        if(warehouseService.getItemById(id).isPresent()){
            warehouseService.sellItem(id);
            return "item sold";
        }
        return "Bad request";
    }

}
