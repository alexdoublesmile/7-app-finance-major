package com.plohoy.financemajor.dao;

import com.plohoy.financemajor.api.dao.CostDao;
import com.plohoy.financemajor.api.domain.Category;
import com.plohoy.financemajor.api.domain.Cost;
import com.plohoy.financemajor.util.CostHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CostDaoTest {
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
        ArrayList<Cost> allCostsFromDB = (ArrayList<Cost>) dao.findAll();

        assertEquals(1, allCostsFromDB.size());
        assertEquals(randomCost, allCostsFromDB.get(0));
    }
}
