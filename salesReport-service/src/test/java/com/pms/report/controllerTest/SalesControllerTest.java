package com.pms.report.controllerTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.pms.report.controller.SalesController;

public class SalesControllerTest {
	
	private MockMvc mockMvc;

    // Setup method to initialize the mockMvc instance
    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new SalesController()).build();
    }

    @Test
    public void testDeleteSalesReport_Successful() throws Exception {
        int id = 1;

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete("/delete/{id}", id))
                .andReturn();

        String responseJson = result.getResponse().getContentAsString();
        // Assert or validate the response as per your requirements
        // For example, you could assert that the response is "true"
        assertTrue(Boolean.parseBoolean(responseJson));
    }


}