package com.visma.warehouseApp.warehouse;

import com.item.ItemDTO;
import com.visma.warehouseApp.exception.NoSuchItemException;
import com.visma.warehouseApp.exception.NotEnoughInStorageException;
import com.visma.warehouseApp.item.Item;
import com.visma.warehouseApp.user.UserRepository;
import com.visma.warehouseApp.userActivity.UserActivityRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class WarehouseServiceTest {

    WarehouseDAO warehouseDAO = Mockito.mock(WarehouseDAO.class);
    UserRepository userRepository = Mockito.mock(UserRepository.class);
    UserActivityRepository userActivityRepository = Mockito.mock(UserActivityRepository.class);
    WarehouseService warehouseService =
            new WarehouseService(warehouseDAO);

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

        assertEquals(item, warehouseService.getItemById(id));
    }

    @Test
    void getItemsTest(){

        Item item1 = new Item(2,new BigDecimal("4.99"), "box", "wooden box", 5);
        Item item2 = new Item(3,new BigDecimal("5.99"), "earphones", "cheap chinese earphones", 10);
        Item item3 = new Item(4,new BigDecimal("6.99"), "cup", "black cup", 15);

        List<Item> items = List.of(item1, item2, item3);

        doReturn(items).when(warehouseDAO).findAll();

        assertEquals(items, warehouseService.getItems());

    }

    @Test
    void sellItemSuccessTest() throws NoSuchItemException, NotEnoughInStorageException {
        doReturn(Optional.of(item)).when(warehouseDAO).findById(eq(item.getId()));

        Integer soldAmount = 3;

        String result = warehouseService.sellItem(item.getId(), soldAmount);

        Mockito.verify(warehouseDAO).setAmountInStorageById(eq(7), eq(item.getId()));

        assertEquals("left in storage: 7", result);
    }

    @Test
    void sellItemNotEnoughInStorageTest() throws NoSuchItemException, NotEnoughInStorageException {

        Mockito.when(
                warehouseDAO.findById(any()))
                .thenReturn(Optional.of(item)
                );

        Integer soldAmount = 11;

        NotEnoughInStorageException thrown =
                Assertions.assertThrows( NotEnoughInStorageException.class,
                () -> { warehouseService.sellItem( eq( item.getId() ), soldAmount); }
                 );

        assertEquals("Not enough items in storage", thrown.getMessage());

    }

    @Test
    void sellItemNoSuchIdTest() {

        Mockito.when(warehouseDAO.findById(any())).thenReturn(Optional.empty());

        NoSuchItemException thrown =
                Assertions.assertThrows(
                        NoSuchItemException.class,
                        () -> { warehouseService.sellItem(anyInt(), 5); }
                );

        assertEquals("No item with such id", thrown.getMessage());
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

        Item result = warehouseService.saveItem(itemDto);

        Mockito.verify(warehouseDAO, times(1))
                .save(any(Item.class));

        assertEquals(itemDto.getId(), result.getId());
        assertEquals(itemDto.getAmountInStorage(), result.getAmountInStorage());

    }

    @Test
    void isItemExistsByIdTrueTest(){
        Mockito
                .when(warehouseDAO.existsById( eq(item.getId()) ))
                .thenReturn(true);

        assertTrue(warehouseService.isItemExistsById(item.getId()));

    }

    @Test
    void isItemExistsByIdFalseTest(){
        Mockito
                .when(warehouseDAO.existsById(anyInt()))
                .thenReturn(false);

        assertFalse(warehouseService.isItemExistsById(anyInt()));

    }
}