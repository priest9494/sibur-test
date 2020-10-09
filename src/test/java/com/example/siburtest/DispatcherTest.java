package com.example.siburtest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DispatcherTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    public void getOrders() throws Exception {
        this.mockMvc.perform(get("/dispatchers/1/orders"))
                .andExpect(status().isOk());
    }

    @Test
    public void addOrder() throws Exception {
        this.mockMvc.perform(patch("/dispatchers/1/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"orderId\": 1, \"status\": \"IN_PROGRESS\"}"))
                .andExpect(status().isOk());
    }
}