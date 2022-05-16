package com.xiw.pattern.builder;

/**
 * @author xiwang
 * @apiNote
 * @since 2022-05-16 16:16
 */
public class Coke extends ColdDrink {

   @Override
   public float price() {
      return 30.0f;
   }

   @Override
   public String name() {
      return "Coke";
   }
}
