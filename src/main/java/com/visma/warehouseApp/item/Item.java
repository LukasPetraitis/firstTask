package com.visma.warehouseApp.item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private BigDecimal price;
    private String name;
    private String description;
    private Integer amountInStorage;

    public Item(BigDecimal price, String name, String description, Integer amountInStorage) {
        this.price = price;
        this.name = name;
        this.description = description;
        this.amountInStorage = amountInStorage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id.equals(item.id) &&
                price.equals(item.price) &&
                name.equals(item.name) &&
                description.equals(item.description) &&
                amountInStorage.equals(item.amountInStorage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, name, description, amountInStorage);
    }
}