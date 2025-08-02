package com.basavarajpatil.designPatterns.creational.builder.mealOrderingSystem;

/**
 * Concrete builder for creating deluxe meals.
 * This builder creates premium meals with high-quality ingredients
 * and larger portions compared to standard meals.
 * 
 * @author Basavaraj Patil
 * @version 1.0
 */
public class DeluxeMealBuilder extends AbstractMealBuilder {
    
    /**
     * Creates a new deluxe meal builder.
     */
    public DeluxeMealBuilder() {
        super(MealType.DELUXE);
    }
    
    @Override
    protected MealItem selectMainItem() {
        return new MealItem(MealItemType.STEAK, "Perfectly grilled 12oz ribeye with garlic butter", 5.00);
    }
    
    @Override
    protected MealItem selectSideItem() {
        return new MealItem(MealItemType.MAC_AND_CHEESE, "Enhanced with truffle oil", 1.50);
    }
    
    @Override
    protected MealItem selectDrinkItem() {
        return new MealItem(MealItemType.ALCOHOLIC_BEVERAGE, "Premium craft beer selection", 2.00);
    }
    
    @Override
    protected MealItem selectDessertItem() {
        return new MealItem(MealItemType.CAKE, "Served with vanilla ice cream and berry compote", 1.50);
    }
    
    @Override
    public boolean validate() {
        if (meal == null) {
            return false;
        }
        
        // Deluxe meals should have at least main item and side
        boolean hasEssentials = meal.hasItem(ItemCategory.MAIN_ITEM) && 
                               meal.hasItem(ItemCategory.SIDE);
        
        // Check minimum price requirement for deluxe meals (base prices + premium additions)
        boolean meetsMinimumPrice = meal.getTotalPrice() >= 30.00;
        
        return hasEssentials && meetsMinimumPrice;
    }
    
    @Override
    public String getBuildSummary() {
        if (meal == null) {
            return "DeluxeMealBuilder: Not initialized";
        }
        
        StringBuilder summary = new StringBuilder();
        summary.append("DeluxeMealBuilder Summary:\n");
        summary.append(String.format("  Order ID: %s\n", meal.getOrderId()));
        summary.append(String.format("  Customer: %s\n", meal.getCustomerName()));
        summary.append(String.format("  Items: %d\n", meal.getAllItems().size()));
        summary.append(String.format("  Total Price: $%.2f\n", meal.getTotalPrice()));
        summary.append(String.format("  Is Valid: %s\n", validate()));
        summary.append(String.format("  Is Complete: %s\n", meal.isComplete()));
        
        if (!meal.getSpecialInstructions().isEmpty()) {
            summary.append("  Special Instructions:\n");
            meal.getSpecialInstructions().forEach(instruction -> 
                summary.append(String.format("    • %s\n", instruction)));
        }
        
        return summary.toString();
    }
}
