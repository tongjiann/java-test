package com.xiw.pattern.builder;

import org.junit.Test;

/**
 * @author xiwang
 * @apiNote
 * @since 2022-05-16 16:19
 */
public class BuilderTest {
    @Test
    public void test(){
        MealBuilder builder = new MealBuilder();
        Meal meal = builder.prepareNonVegMeal();
        System.out.println(meal.getCost());
        meal.showItems();
    }
}
