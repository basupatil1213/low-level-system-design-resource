package com.basavarajpatil.designPatterns.creational.builder.mealOrderingSystem;

/**
 * Builder interface for constructing meals step by step.
 * This interface defines the contract for all meal builders in the system,
 * allowing different types of meals to be constructed using the same process.
 * 
 * This is the Builder interface in the Builder pattern.
 * 
 * @author Basavaraj Patil
 * @version 1.0
 */
public interface MealBuilder {
    
    /**
     * Resets the builder to start building a new meal.
     * This method should be called before starting a new meal construction.
     * 
     * @param orderId Unique identifier for the meal order
     * @param customerName Name of the customer (can be null for guest orders)
     * @return This builder instance for method chaining
     */
    MealBuilder reset(String orderId, String customerName);
    
    /**
     * Builds and adds the main item to the meal.
     * The main item is typically the primary component (burger, sandwich, etc.).
     * 
     * @return This builder instance for method chaining
     */
    MealBuilder buildMainItem();
    
    /**
     * Builds and adds a side item to the meal.
     * Side items complement the main course (fries, salad, etc.).
     * 
     * @return This builder instance for method chaining
     */
    MealBuilder buildSide();
    
    /**
     * Builds and adds a drink to the meal.
     * Drinks are beverages that accompany the meal.
     * 
     * @return This builder instance for method chaining
     */
    MealBuilder buildDrink();
    
    /**
     * Builds and adds a dessert to the meal.
     * Desserts are optional sweet items served after the main course.
     * 
     * @return This builder instance for method chaining
     */
    MealBuilder buildDessert();
    
    /**
     * Adds special instructions for meal preparation.
     * 
     * @param instruction Special instruction for the kitchen staff
     * @return This builder instance for method chaining
     */
    MealBuilder addSpecialInstruction(String instruction);
    
    /**
     * Validates the current state of the meal being built.
     * Checks for required components and logical consistency.
     * 
     * @return true if the meal is valid, false otherwise
     */
    boolean validate();
    
    /**
     * Gets the meal type this builder constructs.
     * 
     * @return The meal type
     */
    MealType getMealType();
    
    /**
     * Returns the constructed meal object.
     * This method should be called after all building steps are complete.
     * 
     * @return The completed meal
     * @throws IllegalStateException if the meal is not in a valid state
     */
    Meal getMeal();
    
    /**
     * Gets a summary of what has been built so far.
     * Useful for debugging and progress tracking.
     * 
     * @return A string summary of the current build state
     */
    String getBuildSummary();
}
