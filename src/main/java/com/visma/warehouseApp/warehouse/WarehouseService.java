package com.visma.warehouseApp.warehouse;

import com.item.ItemDTO;
import com.visma.warehouseApp.item.Item;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class WarehouseService {

    private WarehouseDAO warehouseDAO;

    public List<ItemDTO> getItems(){
        List<ItemDTO> itemsDTO = new ArrayList<ItemDTO>();

        for(Item i : warehouseDAO.findAll()){
               ItemDTO item = new ItemDTO(i.getId(),
                       i.getPrice().toString(),
                       i.getName(),
                       i.getDescription(),
                       i.getAmountInStorage());

               itemsDTO.add(item);
        }
        return itemsDTO;
    }

    public Item getItemById(int id) {
        return warehouseDAO.getById(id);
    }
    @Transactional
    public String sellItem(Integer id, Integer amount){
        Optional<Item> item = warehouseDAO.findById(id);

        if(item.isPresent()){
            Integer inStorage = item.get().getAmountInStorage();

            if(inStorage >= amount){

                inStorage = inStorage - amount;
                warehouseDAO.setAmountInStorageById(inStorage, id);
                return "left in storage: " + inStorage;
            }
            return "not enough items in storage";
        }
        return "no item with such id";
    }

    public void saveItem(ItemDTO itemDTO){
        Item item = new Item();

        item.setPrice(new BigDecimal(itemDTO.getPrice()));
        item.setName(itemDTO.getName());
        item.setDescription(itemDTO.getDescription());
        item.setAmountInStorage(itemDTO.getAmountInStorage());

        warehouseDAO.save(item);
    }
}
