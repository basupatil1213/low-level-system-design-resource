package com.basavarajpatil.designPatterns.creational.builder.mealOrderingSystem;

public class KidsMealBuilder implements MealBuilder{

    private Meal meal = new Meal();

    @Override
    public void buildMainItem() {
        meal.setMainItem("Small Burger");
    }

    @Override
    public void buildSide() {
        meal.setSide("Small Fries");
    }

    @Override
    public void buildDrink() {
        meal.setDessert("Orange Juice");
    }

    @Override
    public void buildDessert() {
        meal.setDessert(null);
    }

    @Override
    public Meal getMeal() {
        return meal;
    }
    
}
