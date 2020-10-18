package com.plohoy.financemajor.dao;

import com.plohoy.financemajor.FinanceMajorApplicationTests;
import com.plohoy.financemajor.domain.Category;
import com.plohoy.financemajor.domain.Cost;
import com.plohoy.financemajor.util.CostHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class CostDaoTest extends FinanceMajorApplicationTests {
    @Autowired MongoTemplate mongoTemplate;
    @Autowired CostDao dao;

    @Before
    public void cleanup() {
        mongoTemplate.dropCollection(Cost.class);
    }

    @Test
    public void shouldFindAll() {
        mongoTemplate.insert(new Cost(null, Category.FOOD, 20L, false));
        for (int i = 0; i < 9; i++) {
            mongoTemplate.insert(CostHelper.getRandomCost());
        }

        Collection<Cost> allCosts = dao.findAll();

        assertEquals(allCosts.size(), 10);
    }

    @Test
    public void shouldInsertСost() {
        Cost randomCost = CostHelper.getRandomCost();
        dao.insert(randomCost);
        List<Cost> allCostsFromDB = dao.findAll();

        assertEquals(1, allCostsFromDB.size());
        assertEquals(randomCost, allCostsFromDB.get(0));
    }
}
