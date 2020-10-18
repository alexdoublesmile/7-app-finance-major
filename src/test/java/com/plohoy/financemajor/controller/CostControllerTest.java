package com.plohoy.financemajor.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.plohoy.financemajor.FinanceMajorApplicationTests;
import com.plohoy.financemajor.domain.Cost;
import com.plohoy.financemajor.service.CostService;
import com.plohoy.financemajor.util.CostHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class CostControllerTest extends FinanceMajorApplicationTests {
    @Autowired private MockMvc mockMvc;
    @Autowired private CostService service;
    @Autowired MongoTemplate mongoTemplate;

    @Before
    public void cleanup() {
        mongoTemplate.dropCollection(Cost.class);
    }

    @Test
    public void shouldFindAll() throws Exception {
        Cost cost = CostHelper.getRandomCost();
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/costs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper()
                                .writeValueAsString(cost)))
                .andExpect(MockMvcResultMatchers
                        .status().isOk());

        List<Cost> allFromDB = service.findAll();
        assertEquals(1, allFromDB.size());

        Cost costFromDB = allFromDB.get(0);
        assertEquals(cost.getCategory(), costFromDB.getCategory());
        assertEquals(cost.getAmount(), costFromDB.getAmount());
    }

    @Test
    public void shouldCountBalance() throws Exception {
        Cost firstCost = CostHelper.getRandomCost();
        Cost secondCost = CostHelper.getRandomCost();
        service.insert(firstCost);
        service.insert(secondCost);

        mockMvc.perform(MockMvcRequestBuilders
                    .get("/costs/balance"))
                .andExpect(MockMvcResultMatchers
                        .status().isOk())
                .andExpect(content().string(
                        String.valueOf(
                                Math.addExact((firstCost.isIncome() ? firstCost.getAmount() : 0 - firstCost.getAmount()),
                                        (secondCost.isIncome() ? secondCost.getAmount() : 0 - secondCost.getAmount())))
                        )
                );
    }
}
