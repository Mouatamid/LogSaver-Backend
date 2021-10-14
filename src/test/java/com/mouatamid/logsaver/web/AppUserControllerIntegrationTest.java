package com.mouatamid.logsaver.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.mouatamid.logsaver.responseModels.AppUserResponseModel;
import com.mouatamid.logsaver.responseModels.LogResponseModel;
import lombok.Data;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.google.common.net.HttpHeaders.AUTHORIZATION;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Data
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AppUserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private String token;

    private static AppUserResponseModel appUserResponseModel;

    private static String jwt;

    @BeforeAll
    void setUp() throws Exception {
        MvcResult result = mockMvc.perform(post("/api/user/create").param("email","said@gmail.com").param("password","123456"))
                .andExpect(status().isCreated()).andReturn();
        appUserResponseModel = new AppUserResponseModel(3,"said@gmail.com",new ArrayList<>());
        Assert.assertEquals(objectMapper.writeValueAsString(appUserResponseModel), result.getResponse().getContentAsString());

        MvcResult result1 = mockMvc.perform(post("/login").param("username","said@gmail.com").param("password","123456")).andReturn();
        jwt = result1.getResponse().getHeader("token");
    }

    @Test
    //Saving log
    void b() throws Exception {
        MvcResult result = mockMvc.perform(post("/api/user/savelog").header(AUTHORIZATION,"Bearer " + jwt).param("username", "said@gmail.com")
        .param("startDate","2021-10-14").param("endDate","2021-10-25").param("description","Hello"))
                .andExpect(status().isCreated()).andReturn();
    }

    @Test
    void listLogs() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/user/logs").param("username","said@gmail.com").header(AUTHORIZATION, "Bearer " + jwt)).andReturn();
        String json = result.getResponse().getContentAsString();
        DocumentContext documentContext = JsonPath.parse(json);

        String description = documentContext.read("$[0].description");
        assertEquals("Hello", description);

        String startDate = documentContext.read("$[0].startDate");
        assertEquals("2021-10-14", startDate);

        String endDate = documentContext.read("$[0].endDate");
        assertEquals("2021-10-25", endDate);
    }
}