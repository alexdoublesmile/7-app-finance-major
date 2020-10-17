package com.plohoy.financemajor.service;

import com.plohoy.financemajor.api.dao.CostDao;
import com.plohoy.financemajor.api.domain.Cost;
import com.plohoy.financemajor.api.service.CostService;
import com.plohoy.financemajor.util.CostHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CostServiceTest {
    @Autowired CostService service;
    @MockBean CostDao dao;

    @Test
    public void shouldFindAll() {
        Cost firstCost = CostHelper.getRandomCost();
        Cost secondCost = CostHelper.getRandomCost();
        when(dao.findAll()).thenReturn(asList(firstCost, secondCost));

        Collection<Cost> allCosts = service.findAll();

        assertEquals(2, allCosts.size());
    }

    @Test
    public void shouldInsertCost() {
        Cost firstCost = CostHelper.getRandomCost();
        service.insert(firstCost);
        ArgumentCaptor<Cost> argumentCaptor = ArgumentCaptor.forClass(Cost.class);
        verify(dao).insert(argumentCaptor.capture());

        Cost costFromDb = argumentCaptor.getValue();
        assertEquals(firstCost, costFromDb);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInsertEmptyCategory() {
        Cost emptyCost = CostHelper.getRandomCost();
        emptyCost.setCategory(null);
        service.insert(emptyCost);
    }
}
