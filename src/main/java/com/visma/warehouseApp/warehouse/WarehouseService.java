package com.visma.warehouseApp.warehouse;

import com.visma.warehouseApp.item.Item;
import com.visma.warehouseApp.item.beverage.WaterBottle;
import com.visma.warehouseApp.item.ItemDTO;
import com.visma.warehouseApp.item.tool.MechanicTool;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static com.visma.warehouseApp.item.ItemType.BEVERAGE;
import static com.visma.warehouseApp.item.ItemType.TOOL;

@Service
@Scope("singleton")
public class WarehouseService {

    private final WarehouseRepository warehouseDAO;

    public WarehouseService(WarehouseRepository warehouseDAO) {
        this.warehouseDAO = warehouseDAO;
    }

    public Map<Integer, Item> getItems(){
        return warehouseDAO.getItems();
    }

    public void sellItem(int id){
        warehouseDAO.updateAmount(id);
    }

    public Optional<Item> getItemById(int id) {
        return warehouseDAO.getItemById(id);
    }
    public void saveBeverage(ItemDTO itemDTO) {
            WaterBottle waterBottle = new WaterBottle(
                    new BigDecimal(itemDTO.getPrice()),
                    itemDTO.getName(),
                    itemDTO.getDescription(),
                    itemDTO.getAmountInStorage(),
                    itemDTO.getQuantity());

            warehouseDAO.save(waterBottle);
        }

    public void saveTool(ItemDTO itemDTO){
        MechanicTool mechanicTool = new MechanicTool();

        mechanicTool.setPrice(new BigDecimal(itemDTO.getPrice()));
        mechanicTool.setName(itemDTO.getName());
        mechanicTool.setDescription(itemDTO.getDescription());
        mechanicTool.setAmountInStorage(itemDTO.getAmountInStorage());

        warehouseDAO.save(mechanicTool);
    }
}
