package com.basavarajpatil.designPatterns.creational.builder.mealOrderingSystem;

public class MealDirector {
    private MealBuilder mealBuilder;

    public MealDirector(MealBuilder mealBuilder) {
        this.mealBuilder = mealBuilder;
    }

    public Meal constructMeal() {
        mealBuilder.buildMainItem();
        mealBuilder.buildSide();
        mealBuilder.buildDrink();
        mealBuilder.buildDessert();
        return mealBuilder.getMeal();
    }
}
