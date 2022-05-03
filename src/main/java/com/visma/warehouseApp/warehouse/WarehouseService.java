package com.visma.warehouseApp.warehouse;

import com.visma.warehouseApp.item.Item;
import com.visma.warehouseApp.item.tool.MechanicTool;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@Scope("singleton")
public class WarehouseService {

    private final WarehouseRepository warehouseDAO;

    public WarehouseService(WarehouseRepository warehouseDAO) {
        this.warehouseDAO = warehouseDAO;
    }

    public Set<Item> getItems(){
        return warehouseDAO.getItems();
    }

    public ResponseEntity<String> sellItem(int id){

        Optional<Item> optional = getItems().stream().filter(x -> x.getId() == id).findFirst();

        if(optional.isPresent()){
            Item item = optional.get();

            warehouseDAO.updateAmount(item);

            return new ResponseEntity<>("updated", HttpStatus.OK);

        }
        return new ResponseEntity<>("Bad request", HttpStatus.BAD_REQUEST);
    }

    public Item getItem(int id) {
        Optional<Item> optional = getItems().stream()
                .filter(x -> x.getId() == id)
                .findFirst();

        return optional.orElse(null);
    }

    public void save(MechanicTool mechanicTool) {
        warehouseDAO.save(mechanicTool);
    }
}
