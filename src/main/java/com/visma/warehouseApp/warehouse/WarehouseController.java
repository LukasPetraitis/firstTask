package com.visma.warehouseApp.warehouse;

import com.item.ItemDTO;
import com.visma.warehouseApp.item.Item;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("warehouse")
@AllArgsConstructor
public class WarehouseController {

    private WarehouseService warehouseService;

    @GetMapping("/hello")
    public String getHello(){
        return "hello";
    }

    @GetMapping("/items")
    public List<Item> getItems(){
        return warehouseService.getItems();
    }

    @GetMapping("/items/{id}")
    public Item getItem(@PathVariable int id){
        return warehouseService.getItemById(id);
    }

    @PostMapping("/newItem")
    public String createItem(@RequestBody ItemDTO itemDTO) {

        if(itemDTO == null){
            return "error";
        }

        warehouseService.saveItem(itemDTO);
        return "item saved";
    }

    @GetMapping("/items/{id}/sell/{amount}")
    public String sellItem(@PathVariable Integer id, @PathVariable Integer  amount){
        return warehouseService.sellItem(id, amount);
    }

}
