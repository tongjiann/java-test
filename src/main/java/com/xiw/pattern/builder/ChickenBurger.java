package com.xiw.pattern.builder;

/**
 * @author xiwang
 * @apiNote
 * @since 2022-05-16 16:16
 */
public class ChickenBurger extends Burger {

   @Override
   public float price() {
      return 50.5f;
   }

   @Override
   public String name() {
      return "Chicken Burger";
   }
}