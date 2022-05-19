package com.visma.warehouseApp.warehouse;

import com.item.ItemDTO;
import com.visma.warehouseApp.exception.NoSuchItemException;
import com.visma.warehouseApp.exception.NotEnoughInStorageException;
import com.visma.warehouseApp.item.Item;
import com.visma.warehouseApp.user.UserRepository;
import com.visma.warehouseApp.user.entity.User;
import com.visma.warehouseApp.userActivity.UserActivity;
import com.visma.warehouseApp.userActivity.UserActivityRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseService {

    private WarehouseDAO warehouseDAO;

//    private UserRepository userRepository;
//
//    private UserActivityRepository userActivityRepository;

    public List<Item> getItems() {
        return warehouseDAO.findAll();
    }

    public Item getItemById(int id) {
        return warehouseDAO.getById(id);
    }

    @Transactional
    public String sellItem(Integer id, Integer soldAmount) throws NoSuchItemException, NotEnoughInStorageException {

        Optional<Item> item = warehouseDAO.findById(id);


//        String username = SecurityContextHolder
//                .getContext()
//                .getAuthentication()
//                .getName();

//        User user = userRepository.findByUsername(username);
        if (item.isEmpty()) {
            throw new NoSuchItemException();
        }

        Integer inStorage = item.get().getAmountInStorage();

        if (inStorage < soldAmount) {
            throw new NotEnoughInStorageException();
        }

        inStorage = inStorage - soldAmount;

//        UserActivity userActivity = new UserActivity();
//        userActivity.setUserActivity("Bought " + soldAmount + " of item with id: " + id);
//        userActivity.setUser(user);

//        userActivityRepository.save(userActivity);
        warehouseDAO.setAmountInStorageById(inStorage, id);

        return "left in storage: " + inStorage;
    }
    @Transactional
    public Item saveItem(ItemDTO itemDTO) {
        Item item = new Item();

        BeanUtils.copyProperties(itemDTO, item);

        item.setPrice(new BigDecimal(itemDTO.getPrice()));

        return warehouseDAO.save(item);
    }

    public boolean isItemExistsById(int id) {
        return warehouseDAO.existsById(id);
    }
}
