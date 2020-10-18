package com.plohoy.financemajor.service;

import com.plohoy.financemajor.FinanceMajorApplicationTests;
import com.plohoy.financemajor.dao.CostDao;
import com.plohoy.financemajor.domain.Cost;
import com.plohoy.financemajor.util.CostHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class CostServiceTest extends FinanceMajorApplicationTests {
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

    @Test(expected = NullPointerException.class)
    public void shouldNotInsertEmptyCost() {

        service.insert(null);

        verify(dao, never()).insert(any());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInsertEmptyCategory() {
        Cost emptyCategoryCost = CostHelper.getRandomCost();
        emptyCategoryCost.setCategory(null);

        service.insert(emptyCategoryCost);

        verify(dao, never()).insert(any());
    }

    @Test
    public void shouldCountBalance() {
        Cost firstCost = CostHelper.getRandomCost();
        Cost secondCost = CostHelper.getRandomCost();

        when(dao.findAll()).thenReturn(asList(firstCost, secondCost));

        int balance = service.getBalance();
        assertEquals(
                Math.addExact((firstCost.isIncome() ? firstCost.getAmount() : 0 - firstCost.getAmount()),
                        (secondCost.isIncome() ? secondCost.getAmount() : 0 - secondCost.getAmount())),
                balance);
    }
}
