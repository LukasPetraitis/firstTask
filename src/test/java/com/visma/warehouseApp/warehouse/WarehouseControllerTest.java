package com.visma.warehouseApp.warehouse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.item.ItemDTO;
import com.visma.warehouseApp.item.Item;
import com.visma.warehouseApp.user.entity.User;
import com.visma.warehouseApp.user.UserRepository;
import com.visma.warehouseApp.user.entity.UserRole;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Base64Utils;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.*;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class WarehouseControllerTest {

    @MockBean
    UserRepository userRepository;

    @MockBean
    WarehouseDAO warehouseDAO;
    @Autowired
    private MockMvc mockMvc;
    Item item;
    List<Item> items;
    String itemDtoString;
    ItemDTO itemDto;
    ObjectMapper objectMapper = new ObjectMapper();

    @PostConstruct
    void init() throws JsonProcessingException {

        User user = new User(5, "user", "password", UserRole.USER);

        Mockito.doReturn(eq(user)).when(userRepository.findByUsername("user"));

        item = new Item(
                2,
                new BigDecimal("9.99"),
                "screwdriwer",
                "tool for work",
                10);

        Item item1 = new Item(1, new BigDecimal("4.99"), "box", "wooden box", 5);
        Item item2 = new Item(2, new BigDecimal("5.99"), "earphones", "cheap chinesse earphones", 10);
        Item item3 = new Item(3, new BigDecimal("6.99"), "cup", "black cup", 15);

        items = List.of(item1, item2, item3);

        itemDto = new ItemDTO(
                2,
                "9.99",
                "hammer",
                "good old hammer",
                10);



        itemDtoString = objectMapper.writeValueAsString(itemDto);
    }

    @Test
    public void getItemIntegrationTest() throws Exception {

        doReturn(item).when(warehouseDAO).getById(eq(2));
        doReturn(true).when(warehouseDAO).existsById(eq(2));

        mockMvc
            .perform( get("/warehouse/items/2").header(HttpHeaders.AUTHORIZATION,
                            "Basic " + Base64Utils.encodeToString( "user:password".getBytes() )) )
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().json("{'id': 2, " +
                                                "'price': 9.99, " +
                                                "'name': 'screwdriwer', " +
                                                "'description': 'tool for work', " +
                                                "'amountInStorage': 10}"));
    }

    @Test
    @WithMockUser(username = "user", password = "password", roles = "USER")
    public void getItemNoSuchIdIntegrationTest() throws Exception {

        doReturn(item).when(warehouseDAO).getById(any());
        doReturn(false).when(warehouseDAO).existsById(any());

        mockMvc
            .perform(get("/warehouse/items/1000"))
            .andDo(print())
            .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(username = "user", password = "password", roles = "USER")
    public void getItemsIntegrationTest() throws Exception {

        doReturn(items).when(warehouseDAO).findAll();

        mockMvc
                .perform(get("/warehouse/items"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.*", hasSize(3)))
                .andExpect(jsonPath("$[*].id", containsInAnyOrder(1, 2, 3)));
    }

    @Test
    @WithMockUser(username = "user", password = "password", roles = "USER")
    public void sellItemSuccessIntegrationTest() throws Exception {
        doReturn(Optional.of(item)).when(warehouseDAO).findById(eq(item.getId()));

        Integer soldAmount = 3;

        Integer amountLeftInStorage = item.getAmountInStorage() - soldAmount;

        mockMvc
                .perform(get("/warehouse/items/"+ item.getId()+"/sell/"+ soldAmount))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/plain;charset=UTF-8"))
                .andExpect(content().string("left in storage: " + amountLeftInStorage));
    }

    @Test
    @WithMockUser(username = "user", password = "password", roles = "USER")
    public void sellItemNotEnoughInStorageIntegrationTest() throws Exception {
        doReturn(Optional.of(item)).when(warehouseDAO).findById(eq(item.getId()));

        Integer soldAmount = 11;

        mockMvc
                .perform(get("/warehouse/items/"+ item.getId()+"/sell/"+ soldAmount))
                .andDo(print())
                .andExpect(status().isNotAcceptable())
                .andExpect(content().contentType("text/plain;charset=UTF-8"))
                .andExpect(content().string("Not enough items in storage"));
    }

    @Test
    @WithMockUser(username = "user", password = "password", roles = "USER")
    public void sellItemNotFoundByIdIntegrationTest() throws Exception {

        doReturn( Optional.empty() ).when(warehouseDAO).findById(any());

        mockMvc
                .perform(get("/warehouse/items/"+ 1000 +"/sell/" + 1000))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(username = "user", password = "password", roles = "USER")
    public void createItemWithExistingIdIntegrationTest() throws Exception {

        // Nepavyko su doReturn
        Mockito
                .when(warehouseDAO.existsById( eq(itemDto.getId()) ))
                .thenReturn(true);

        mockMvc
                .perform(post("/warehouse/items")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content( itemDtoString ))
                .andDo(print())
                .andExpect(status().isConflict())
                .andExpect(content().string("Item with such id exists"));
    }

    @Test
    @WithMockUser(username = "user", password = "password", roles = "USER")
    public void createItemIntegrationTest() throws Exception {

        Mockito
            .when(warehouseDAO.save(ArgumentMatchers.any(Item.class)))
            .thenAnswer(i -> i.getArguments()[0]);

        Mockito
            .when(warehouseDAO.existsById( eq(itemDto.getId()) ))
            .thenReturn(false);


        mockMvc
                .perform(post("/warehouse/items")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content( itemDtoString ))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Item saved"));

    }

    @Test
    @WithMockUser(username = "user", password = "password", roles = "USER")
    public void createItemWithNullValueIntegrationTest() throws Exception {

        mockMvc
                .perform(post("/warehouse/items"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(username = "user", password = "password", roles = "USER")
    public void createItemWithNegativeAmountIntegrationTest() throws Exception {

            itemDto.setAmountInStorage( -1 );
            String itemDtoStringWithNegativeAmount = objectMapper.writeValueAsString(itemDto);
            itemDto.setAmountInStorage( 10 );

        mockMvc
                .perform(post("/warehouse/items")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content( itemDtoStringWithNegativeAmount ))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Error"));
    }
}