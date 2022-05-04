package com.visma.warehouseApp.item;

import org.springframework.stereotype.Component;


public class ItemDTO {

    private String itemType;
    private int id;
    private String price;
    private String name;
    private String description;
    private int amountInStorage;
    private double quantity;

    public ItemDTO(String itemType,
                   int id,
                   String price,
                   String name,
                   String description,
                   int amountInStorage,
                   double quantity) {
        this.itemType = itemType;
        this.id = id;
        this.price = price;
        this.name = name;
        this.description = description;
        this.amountInStorage = amountInStorage;
        this.quantity = quantity;
    }


//    public ItemDTO(String itemType, int id, String price, String name, String description, int amountInStorage) {
//        this.itemType = itemType;
//        this.id = id;
//        this.price = price;
//        this.name = name;
//        this.description = description;
//        this.amountInStorage = amountInStorage;
//    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmountInStorage() {
        return amountInStorage;
    }

    public void setAmountInStorage(int amountInStorage) {
        this.amountInStorage = amountInStorage;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

}
