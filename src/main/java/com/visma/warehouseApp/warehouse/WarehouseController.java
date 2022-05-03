package com.visma.warehouseApp.warehouse;

import com.visma.warehouseApp.item.Item;
import com.visma.warehouseApp.item.tool.MechanicTool;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        return "Sveiki!!";
    }

    @GetMapping("/items")
    public Set<Item> getItems(){
        return warehouseService.getItems();
    }

    @GetMapping("/items/{id}")
    public Item getItems(@PathVariable int id){
        return warehouseService.getItem(id);
    }
    @PostMapping("/newItem")
    public String createMechanicalTool(@RequestBody MechanicTool mechanicTool){
        if(mechanicTool.equals(null)){
            return "Error";
        }
        warehouseService.save(mechanicTool);
        return "Tool saved";
    }

    @PutMapping("/sellItem/{id}")
    public ResponseEntity<String> sellItem(@PathVariable int id){
        return warehouseService.sellItem(id);
    }

}
