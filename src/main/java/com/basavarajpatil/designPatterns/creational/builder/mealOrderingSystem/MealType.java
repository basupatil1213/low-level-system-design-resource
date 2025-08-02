package com.basavarajpatil.designPatterns.creational.builder.mealOrderingSystem;

/**
 * Enumeration representing different types of meals available in the ordering system.
 * Each meal type has predefined characteristics and target demographics.
 * 
 * @author Basavaraj Patil
 * @version 1.0
 */
public enum MealType {
    
    /**
     * Child-friendly meals with smaller portions and kid-approved items
     */
    KIDS("Kids Meal", "Child-friendly meals with smaller portions", 6.99, 400),
    
    /**
     * Standard adult meals with regular portions
     */
    REGULAR("Regular Meal", "Standard adult meals with regular portions", 10.99, 650),
    
    /**
     * Premium meals with larger portions and higher-quality ingredients
     */
    DELUXE("Deluxe Meal", "Premium meals with larger portions and quality ingredients", 15.99, 850),
    
    /**
     * Health-conscious meals with low-calorie, nutritious options
     */
    HEALTHY("Healthy Choice Meal", "Health-conscious meals with nutritious options", 12.99, 500),
    
    /**
     * Plant-based meals for vegetarian customers
     */
    VEGETARIAN("Vegetarian Meal", "Plant-based meals for vegetarian customers", 11.99, 550),
    
    /**
     * Value meals for budget-conscious customers
     */
    VALUE("Value Meal", "Budget-friendly meals with essential items", 7.99, 600),
    
    /**
     * Custom meals built from individual selections
     */
    CUSTOM("Custom Meal", "Personalized meals built from individual selections", 0.0, 0);
    
    private final String displayName;
    private final String description;
    private final double basePrice;
    private final int targetCalories;
    
    MealType(String displayName, String description, double basePrice, int targetCalories) {
        this.displayName = displayName;
        this.description = description;
        this.basePrice = basePrice;
        this.targetCalories = targetCalories;
    }
    
    /**
     * Gets the human-readable display name for this meal type.
     * 
     * @return The display name
     */
    public String getDisplayName() {
        return displayName;
    }
    
    /**
     * Gets the description of this meal type.
     * 
     * @return The meal type description
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Gets the base price for this meal type (before item costs).
     * 
     * @return The base price
     */
    public double getBasePrice() {
        return basePrice;
    }
    
    /**
     * Gets the target calorie content for this meal type.
     * 
     * @return The target calories
     */
    public int getTargetCalories() {
        return targetCalories;
    }
    
    /**
     * Checks if this meal type is suitable for children.
     * 
     * @return true if child-friendly, false otherwise
     */
    public boolean isChildFriendly() {
        return this == KIDS;
    }
    
    /**
     * Checks if this meal type focuses on health and nutrition.
     * 
     * @return true if health-focused, false otherwise
     */
    public boolean isHealthFocused() {
        return this == HEALTHY || this == VEGETARIAN;
    }
    
    /**
     * Checks if this meal type is budget-oriented.
     * 
     * @return true if budget-oriented, false otherwise
     */
    public boolean isBudgetFriendly() {
        return this == VALUE || this == KIDS;
    }
    
    /**
     * Checks if this meal type requires special dietary considerations.
     * 
     * @return true if special dietary needs, false otherwise
     */
    public boolean hasSpecialDietaryNeeds() {
        return this == VEGETARIAN || this == HEALTHY;
    }
    
    @Override
    public String toString() {
        return String.format("%s (Base: $%.2f, Target: %d cal)", 
                displayName, basePrice, targetCalories);
    }
}
