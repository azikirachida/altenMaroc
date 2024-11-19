package com.example.alten.controller;

import com.example.alten.AltenApplication;
import com.example.alten.entity.Product;
import com.example.alten.repository.ProductRepository;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import java.math.BigDecimal;
import java.util.Date;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes = AltenApplication.class)
@SpringBootTest
public class ProductControllerTest {

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    public void init() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        Product product1 = new Product(1,"123456","TV LG","tv 60","tv_tg.jpg","tv", BigDecimal.valueOf(100),100,"ABC123456","INSTOCK",123456,6,new Date(),null);
        Product product2 = new Product(2,"225588","TV SONY","tv 55","tv_sony.jpg","tv",BigDecimal.valueOf(200),200,"FDRY123","OUTOFSTOCK",485697,6,new Date(),null);
        productRepository.save(product1);
        productRepository.save(product2);
    }

    @AfterEach
    public void finish() {
        productRepository.flush();
    }
    @Test
    public void getProductById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/products/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.name", Matchers.is("TV LG")))
                .andExpect(jsonPath("$.code", Matchers.is("123456")));
    }
    @Test
    public void addProduct() throws Exception {
        Product product = new Product(1,"123456","TV LG","tv 60","tv_tg.jpg","tv", BigDecimal.valueOf(100),100,"ABC123456","INSTOCK",123456,6,new Date(),null);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/users").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isCreated());
    }
}

