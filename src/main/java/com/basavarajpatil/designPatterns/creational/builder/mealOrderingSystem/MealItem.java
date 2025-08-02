package com.basavarajpatil.designPatterns.creational.builder.mealOrderingSystem;

import java.util.Objects;

/**
 * Represents an individual meal item with its properties and characteristics.
 * This class encapsulates all the information about a meal component including
 * nutritional information, pricing, and dietary classifications.
 * 
 * @author Basavaraj Patil
 * @version 1.0
 */
public class MealItem {
    
    private final MealItemType itemType;
    private final String customization;
    private final double adjustedPrice;
    
    /**
     * Creates a standard meal item without customization.
     * 
     * @param itemType The type of meal item
     */
    public MealItem(MealItemType itemType) {
        this(itemType, null, 0.0);
    }
    
    /**
     * Creates a customized meal item with potential price adjustment.
     * 
     * @param itemType The type of meal item
     * @param customization Custom modifications (e.g., "No pickles", "Extra cheese")
     * @param priceAdjustment Additional price for customizations
     */
    public MealItem(MealItemType itemType, String customization, double priceAdjustment) {
        this.itemType = Objects.requireNonNull(itemType, "Item type cannot be null");
        this.customization = customization;
        this.adjustedPrice = itemType.getPrice() + priceAdjustment;
    }
    
    /**
     * Gets the meal item type.
     * 
     * @return The item type
     */
    public MealItemType getItemType() {
        return itemType;
    }
    
    /**
     * Gets the display name of this meal item.
     * 
     * @return The display name
     */
    public String getName() {
        return itemType.getDisplayName();
    }
    
    /**
     * Gets the category this item belongs to.
     * 
     * @return The item category
     */
    public ItemCategory getCategory() {
        return itemType.getCategory();
    }
    
    /**
     * Gets the final price including any customization adjustments.
     * 
     * @return The adjusted price
     */
    public double getPrice() {
        return adjustedPrice;
    }
    
    /**
     * Gets the base calorie content (customizations may affect actual calories).
     * 
     * @return The calorie count
     */
    public int getCalories() {
        return itemType.getCalories();
    }
    
    /**
     * Gets any customization notes for this item.
     * 
     * @return The customization string, or null if none
     */
    public String getCustomization() {
        return customization;
    }
    
    /**
     * Checks if this item has any customizations.
     * 
     * @return true if customized, false otherwise
     */
    public boolean isCustomized() {
        return customization != null && !customization.trim().isEmpty();
    }
    
    /**
     * Checks if this item is vegetarian-friendly.
     * 
     * @return true if vegetarian, false otherwise
     */
    public boolean isVegetarian() {
        return itemType.isVegetarian();
    }
    
    /**
     * Checks if this item is considered healthy.
     * 
     * @return true if healthy, false otherwise
     */
    public boolean isHealthy() {
        return itemType.isHealthy();
    }
    
    /**
     * Creates a formatted string representation suitable for receipts or displays.
     * 
     * @return Formatted item description
     */
    public String getFormattedDescription() {
        StringBuilder description = new StringBuilder();
        description.append(String.format("%-20s", getName()));
        description.append(String.format("$%6.2f", getPrice()));
        description.append(String.format(" (%d cal)", getCalories()));
        
        if (isCustomized()) {
            description.append("\n  ↳ ").append(customization);
        }
        
        return description.toString();
    }
    
    @Override
    public String toString() {
        if (isCustomized()) {
            return String.format("%s (Custom: %s) - $%.2f", getName(), customization, getPrice());
        } else {
            return String.format("%s - $%.2f", getName(), getPrice());
        }
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MealItem mealItem = (MealItem) o;
        return itemType == mealItem.itemType && Objects.equals(customization, mealItem.customization);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(itemType, customization);
    }
}
