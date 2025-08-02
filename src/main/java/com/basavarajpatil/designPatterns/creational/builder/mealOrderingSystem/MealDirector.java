package com.basavarajpatil.designPatterns.creational.builder.mealOrderingSystem;

/**
 * Director class that knows how to construct complete meals using builders.
 * This class encapsulates the algorithm for creating well-formed meals,
 * implementing the Director pattern alongside the Builder pattern.
 * 
 * @author Basavaraj Patil
 * @version 1.0
 */
public class MealDirector {
    private MealBuilder mealBuilder;

    /**
     * Creates a new meal director with the specified builder.
     * 
     * @param mealBuilder The builder to use for constructing meals
     */
    public MealDirector(MealBuilder mealBuilder) {
        this.mealBuilder = mealBuilder;
    }

    /**
     * Constructs a complete meal using the configured builder.
     * This method encapsulates the algorithm for creating a standard meal
     * with all essential components.
     * 
     * @param orderId Unique identifier for the meal order
     * @param customerName Name of the customer
     * @return The constructed meal
     */
    public Meal constructMeal(String orderId, String customerName) {
        return mealBuilder
                .reset(orderId, customerName)
                .buildMainItem()
                .buildSide()
                .buildDrink()
                .buildDessert()
                .getMeal();
    }

    /**
     * Constructs a basic meal with only essential items.
     * 
     * @param orderId Unique identifier for the meal order
     * @param customerName Name of the customer
     * @return The constructed basic meal
     */
    public Meal constructBasicMeal(String orderId, String customerName) {
        return mealBuilder
                .reset(orderId, customerName)
                .buildMainItem()
                .buildSide()
                .buildDrink()
                .getMeal();
    }

    /**
     * Changes the builder used by this director.
     * 
     * @param mealBuilder The new builder to use
     */
    public void setBuilder(MealBuilder mealBuilder) {
        this.mealBuilder = mealBuilder;
    }

    /**
     * Gets the current builder being used.
     * 
     * @return The current meal builder
     */
    public MealBuilder getBuilder() {
        return mealBuilder;
    }
}
