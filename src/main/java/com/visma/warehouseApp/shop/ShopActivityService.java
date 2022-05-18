package com.visma.warehouseApp.shop;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopActivityService {

    @Autowired
    private ShopActivityRepository shopActivityRepository;

    public Shop saveShopInfo(Shop shop){
        return shopActivityRepository.save(shop);
    }
}
