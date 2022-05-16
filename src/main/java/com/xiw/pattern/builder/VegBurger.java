package com.xiw.pattern.builder;

/**
 * @author xiwang
 * @apiNote
 * @since 2022-05-16 16:16
 */
public class VegBurger extends Burger {

   @Override
   public float price() {
      return 25.0f;
   }

   @Override
   public String name() {
      return "Veg Burger";
   }
}