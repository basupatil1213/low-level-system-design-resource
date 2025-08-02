package com.basavarajpatil.designPatterns.creational.builder.mealOrderingSystem;

public interface MealBuilder {
    void buildMainItem();

    void buildSide();

    void buildDrink();

    void buildDessert();

    Meal getMeal();
}
