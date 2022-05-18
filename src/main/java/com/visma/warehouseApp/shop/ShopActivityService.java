package com.visma.warehouseApp.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopActivityService {

    @Autowired
    private ShopActivityRepository shopActivityRepository;

    public ShopActivity saveShopInfo(ShopActivity shopActivity){
        return shopActivityRepository.save(shopActivity);
    }
}
