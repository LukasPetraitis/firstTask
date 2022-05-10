package com.visma.warehouseApp.warehouse;

import com.item.ItemDTO;
import com.visma.warehouseApp.item.Item;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class WarehouseServiceTest {


    WarehouseDAO warehouseDAO = Mockito.mock(WarehouseDAO.class);

    WarehouseService warehouseService = new WarehouseService(warehouseDAO);

    Item item = new Item(
            2,
            new BigDecimal("9.99"),
            "hammer",
            "good old hammer",
            10);

    @Test
    void getItemTest() {

        Integer id = item.getId();

        Mockito.when(warehouseDAO.getById(id)).thenReturn(item);

        assertEquals(item.getName(),
                warehouseService.getItemById(id).getName());

        assertEquals(item.getPrice(), warehouseDAO.getById(id).getPrice());

        assertEquals(item.getDescription(),
                warehouseDAO.getById(id).getDescription());

        assertEquals(item.getAmountInStorage(),
                warehouseDAO.getById(id).getAmountInStorage());

    }

    @Test
    void getItemsTest(){

        Item item1 = new Item(2,new BigDecimal("4.99"), "box", "wooden box", 5);
        Item item2 = new Item(3,new BigDecimal("5.99"), "earphones", "cheap chinese earphones", 10);
        Item item3 = new Item(4,new BigDecimal("6.99"), "cup", "black cup", 15);

        List<Item> items = new ArrayList<>(Arrays.asList(item1, item2, item3));

        Mockito.when(warehouseDAO.findAll()).thenReturn(items);

        assertEquals(items, warehouseService.getItems());

    }

    @Test
    void sellItemSuccesTest(){

        Integer amount = 3;

        Mockito.doReturn(Optional.of(item)).when(warehouseDAO).findById(any());

        String result = warehouseService.sellItem(item.getId(), amount);

        Mockito.verify(warehouseDAO).setAmountInStorageById(eq(7), any());

        assertEquals("left in storage: 7", result);
    }

    @Test
    void sellItemNotEnoughInStorageTest(){

        Integer amount = 11;

        Mockito.when(warehouseDAO.findById(any())).thenReturn(Optional.of(item));

        String result = warehouseService.sellItem(item.getId(), amount);

        assertEquals("not enough items in storage", result);
    }

    @Test
    void sellItemNoSuchIdTest(){

        Mockito.when(warehouseDAO.findById(any())).thenReturn(Optional.empty());

        String result = warehouseService.sellItem(anyInt(), 5);

        assertEquals("no item with such id", result);
    }

    @Test
    void saveItemTest(){

        ItemDTO itemDto = new ItemDTO(
                2,
                "9.99",
                "hammer",
                "good old hammer",
                10);

        Mockito.when(warehouseDAO.save(any(Item.class))).thenAnswer(i -> i.getArguments()[0]);

        Item savedItem = warehouseService.saveItem(itemDto);

        Mockito.verify(warehouseDAO, times(1)).save(savedItem);

    }

}