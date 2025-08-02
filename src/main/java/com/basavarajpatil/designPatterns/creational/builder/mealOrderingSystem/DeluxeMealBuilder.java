package com.basavarajpatil.designPatterns.creational.builder.mealOrderingSystem;

class DeluxeMealBuilder implements MealBuilder {
    private Meal meal = new Meal();

    public void buildMainItem() {
        meal.setMainItem("Double Burger");
    }

    public void buildSide() {
        meal.setSide("Fries");
    }

    public void buildDrink() {
        meal.setDrink("Coke");
    }

    public void buildDessert() {
        meal.setDessert("Ice Cream");
    }

    public Meal getMeal() {
        return meal;
    }
}
