package com.visma.warehouseApp.shop;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopActivityRepository extends JpaRepository<Shop, Integer> {



}
