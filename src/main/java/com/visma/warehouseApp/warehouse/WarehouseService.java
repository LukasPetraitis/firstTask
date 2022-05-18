package com.visma.warehouseApp.warehouse;

import com.item.ItemDTO;
import com.visma.warehouseApp.exception.NoSuchItemException;
import com.visma.warehouseApp.exception.NotEnoughInStorageException;
import com.visma.warehouseApp.item.Item;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class WarehouseService {

    private WarehouseDAO warehouseDAO;

    public List<Item> getItems() {
        return warehouseDAO.findAll();
    }

    public Item getItemById(int id) {
        return warehouseDAO.getById(id);
    }

    @Transactional
    public String sellItem(Integer id, Integer soldAmount) throws NoSuchItemException, NotEnoughInStorageException {
        Optional<Item> item = warehouseDAO.findById(id);

        if (item.isEmpty()) {
            throw new NoSuchItemException();
        }

        Integer inStorage = item.get().getAmountInStorage();

        if (inStorage < soldAmount) {
            throw new NotEnoughInStorageException();
        }

        inStorage = inStorage - soldAmount;

        warehouseDAO.setAmountInStorageById(inStorage, id);
        return "left in storage: " + inStorage;
    }

    public Item saveItem(ItemDTO itemDTO) {
        Item item = new Item();

        // biblioteka properties setinimui
        BeanUtils.copyProperties(itemDTO, item);

        // ir nebereikia daryti sitaip vienodu tipu kintamiesiems
        item.setPrice(new BigDecimal(itemDTO.getPrice()));

        return warehouseDAO.save(item);
    }

    public boolean isItemExistsById(int id) {
        return warehouseDAO.existsById(id);
    }
}
