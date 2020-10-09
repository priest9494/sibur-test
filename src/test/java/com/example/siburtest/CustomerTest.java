package com.example.siburtest;

import com.example.siburtest.controllers.CustomerController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CustomerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    CustomerController customerController;

    @Test
    public void getOrders() throws Exception {
        this.mockMvc.perform(get("/customers/1/orders"))
                .andExpect(status().isOk());
    }

    @Test
    public void getOrder() throws Exception {
        this.mockMvc.perform(get("/customers/1/orders/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void addOrder() throws Exception {
        this.mockMvc.perform(post("/customers/1/orders")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{ \"transportTypeId\": 1, \"fullName\": \"Иванов Иван Иванович\", \"phoneNumber\": \"88005553535\" }"))
        .andExpect(status().isOk());
    }
}
