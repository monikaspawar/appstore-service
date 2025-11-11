package com.example.appstore_service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SampleController.class)
class SampleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testAddAndGetItem() throws Exception {
        mockMvc.perform(post("/api/items?id=1&name=Item1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Added"));

        mockMvc.perform(get("/api/items/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Item1"));
    }

    @Test
    void testUpdateItem() throws Exception {
        mockMvc.perform(post("/api/items?id=2&name=Item2"))
                .andExpect(status().isOk());

        mockMvc.perform(put("/api/items/2?name=NewItem2"))
                .andExpect(status().isOk())
                .andExpect(content().string("Updated"));

        mockMvc.perform(get("/api/items/2"))
                .andExpect(status().isOk())
                .andExpect(content().string("NewItem2"));
    }

    @Test
    void testDeleteItem() throws Exception {
        mockMvc.perform(post("/api/items?id=3&name=Item3"))
                .andExpect(status().isOk());

        mockMvc.perform(delete("/api/items/3"))
                .andExpect(status().isOk())
                .andExpect(content().string("Deleted"));

        mockMvc.perform(get("/api/items/3"))
                .andExpect(status().isOk())
                .andExpect(content().string("Not Found"));
    }
}