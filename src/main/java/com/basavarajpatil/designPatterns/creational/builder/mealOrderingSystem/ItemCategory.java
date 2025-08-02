package com.basavarajpatil.designPatterns.creational.builder.mealOrderingSystem;

/**
 * Enumeration representing different categories of meal items.
 * Used to classify meal components for organization and validation.
 * 
 * @author Basavaraj Patil
 * @version 1.0
 */
public enum ItemCategory {
    
    /**
     * Main course items (burgers, sandwiches, etc.)
     */
    MAIN_ITEM("Main Item", "The primary component of the meal"),
    
    /**
     * Side dish items (fries, salads, etc.)
     */
    SIDE("Side", "Complementary items served alongside the main course"),
    
    /**
     * Beverage items (sodas, juices, etc.)
     */
    DRINK("Drink", "Beverages to accompany the meal"),
    
    /**
     * Dessert items (ice cream, cakes, etc.)
     */
    DESSERT("Dessert", "Sweet items served after the main course");
    
    private final String displayName;
    private final String description;
    
    ItemCategory(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }
    
    /**
     * Gets the human-readable display name for this category.
     * 
     * @return The display name
     */
    public String getDisplayName() {
        return displayName;
    }
    
    /**
     * Gets the description of this category.
     * 
     * @return The category description
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Checks if this category is essential for a complete meal.
     * 
     * @return true if essential, false otherwise
     */
    public boolean isEssential() {
        return this == MAIN_ITEM || this == DRINK;
    }
    
    @Override
    public String toString() {
        return displayName;
    }
}
