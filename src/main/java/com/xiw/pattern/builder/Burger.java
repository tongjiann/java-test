package com.xiw.pattern.builder;

/**
 * @author xiwang
 * @apiNote
 * @since 2022-05-16 16:15
 */
public abstract class Burger implements Item {

   @Override
   public Packing packing() {
      return new Wrapper();
   }

   @Override
   public abstract float price();
}