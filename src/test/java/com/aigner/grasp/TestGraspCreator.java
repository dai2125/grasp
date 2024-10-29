package com.aigner.grasp;

import com.aigner.grasp.fridge.FoodFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class TestGraspCreator {

    @Autowired
    FoodFactory foodFactory;

    @Test
    void createFoodItem() {
        Object object = foodFactory.createFood("Cheese", 189.01, new Date());
        assertNotNull(object);
        assertEquals("Cheese", object.getClass().toString());
    }
}
