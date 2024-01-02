package gsc.projects.orderservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import gsc.projects.orderservice.dto.OrderCreateDto;
import gsc.projects.orderservice.dto.OrderDto;
import gsc.projects.orderservice.dto.OrderEvent;
import gsc.projects.orderservice.dto.UserDto;
import gsc.projects.orderservice.model.Order;
import gsc.projects.orderservice.repository.OrderRepository;
import gsc.projects.orderservice.service.APIClient;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
@EnableFeignClients
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private APIClient apiClient;

    private Order order;
    private OrderCreateDto orderCreateDto;
    private OrderDto orderDto;
    private UserDto userDto;
    private OrderEvent orderEvent;

    @BeforeEach
    void setup(){
        orderRepository.deleteAll();

        orderCreateDto = new OrderCreateDto();
        orderCreateDto.setRestaurantEmail("restaurant@email.com");
        orderCreateDto.setUserEmail("test@email.com");

        order = new Order();
        order.setUuidOrder(orderCreateDto.getUuidOrder());
        order.setRestaurantEmail(orderCreateDto.getRestaurantEmail());
        order.setUserEmail(orderCreateDto.getUserEmail());

        orderDto = new OrderDto();
        orderDto.setUuidOrder(order.getUuidOrder());
        orderDto.setRestaurantEmail(order.getRestaurantEmail());
        orderDto.setUserEmail(order.getUserEmail());

        orderEvent = new OrderEvent();
        orderEvent.setUuidOrder(order.getUuidOrder());
        orderEvent.setRestaurantEmail(order.getRestaurantEmail());
        orderEvent.setUserEmail(order.getUserEmail());

        userDto = apiClient.getUser(order.getUserEmail());
    }


    @Nested
    @Tag("Order service integration tests")
    public class OrderIntegrationTest{

        @Test
        @DisplayName("Create a non exists user and return 200")
        public void createANonExistsUser200() throws Exception {

            ResultActions response = mockMvc.perform(post("/api/order")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsBytes(orderCreateDto)));

            response.andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isCreated());

        }

        @Test
        @DisplayName("Get an order with exists uuid return 200")
        public void getAnOrderExists200() throws Exception {

            order.setUuidOrder(UUID.randomUUID());

            orderRepository.save(order);

            orderDto.setUuidOrder(order.getUuidOrder());

            ResultActions response = mockMvc.perform(get("/api/order/uuid/{uuidOrder}", order.getUuidOrder()));

            response.andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.uuidOrder", is(orderDto.getUuidOrder().toString())));
        }

        @Test
        @DisplayName("Try to get and order with non exists uuid return 404")
        public void tryToGetAnOrderNonExistsUUID404() throws Exception {

            ResultActions response = mockMvc.perform(get("/api/order/uuid/{uuidOrder}", order.getUuidOrder()));

            response.andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isNotFound());
        }
    }
}