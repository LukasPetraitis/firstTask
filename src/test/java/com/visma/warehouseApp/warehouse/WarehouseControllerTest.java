package com.visma.warehouseApp.warehouse;

import com.item.ItemDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WarehouseControllerTest {

    @Autowired
    WarehouseController warehouseController;



    ItemDTO item1 = new ItemDTO(1, "4.99", "box", "wooden box", 5);
    ItemDTO item2 = new ItemDTO(2, "5.99", "earphones", "cheap chinesse earphones", 10);
    ItemDTO item3 = new ItemDTO(3, "6.99", "cup", "black cup", 15);

    @Test
    public void getHello() throws Exception {
        assertEquals("hello", warehouseController.getHello());
    }
}

