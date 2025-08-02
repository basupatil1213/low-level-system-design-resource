package com.basavarajpatil.designPatterns.creational.builder.mealOrderingSystem;

/**
 * Enumeration representing different types of meal items available in the ordering system.
 * This provides type safety and a clear contract for available meal components.
 * 
 * @author Basavaraj Patil
 * @version 1.0
 */
public enum MealItemType {
    
    // Main Items
    BURGER("Burger", ItemCategory.MAIN_ITEM, 8.99, 450),
    CHICKEN_SANDWICH("Chicken Sandwich", ItemCategory.MAIN_ITEM, 9.99, 520),
    VEGGIE_BURGER("Veggie Burger", ItemCategory.MAIN_ITEM, 7.99, 380),
    GRILLED_CHICKEN("Grilled Chicken", ItemCategory.MAIN_ITEM, 11.99, 320),
    FISH_FILLET("Fish Fillet", ItemCategory.MAIN_ITEM, 10.99, 410),
    STEAK("Premium Steak", ItemCategory.MAIN_ITEM, 19.99, 650),
    
    // Side Items
    FRENCH_FRIES("French Fries", ItemCategory.SIDE, 3.99, 240),
    ONION_RINGS("Onion Rings", ItemCategory.SIDE, 4.49, 280),
    COLESLAW("Coleslaw", ItemCategory.SIDE, 2.99, 120),
    GARDEN_SALAD("Garden Salad", ItemCategory.SIDE, 4.99, 80),
    MOZZARELLA_STICKS("Mozzarella Sticks", ItemCategory.SIDE, 5.49, 320),
    MAC_AND_CHEESE("Gourmet Mac and Cheese", ItemCategory.SIDE, 7.99, 420),
    
    // Drinks
    COLA("Cola", ItemCategory.DRINK, 2.49, 140),
    LEMONADE("Lemonade", ItemCategory.DRINK, 2.99, 110),
    ICED_TEA("Iced Tea", ItemCategory.DRINK, 2.29, 70),
    ORANGE_JUICE("Orange Juice", ItemCategory.DRINK, 3.49, 120),
    WATER("Water", ItemCategory.DRINK, 1.99, 0),
    MILKSHAKE("Milkshake", ItemCategory.DRINK, 4.99, 350),
    ALCOHOLIC_BEVERAGE("Premium Wine", ItemCategory.DRINK, 12.99, 150),
    
    // Desserts
    ICE_CREAM("Ice Cream", ItemCategory.DESSERT, 3.99, 200),
    CHOCOLATE_CAKE("Chocolate Cake", ItemCategory.DESSERT, 4.99, 380),
    APPLE_PIE("Apple Pie", ItemCategory.DESSERT, 3.49, 290),
    COOKIES("Cookies", ItemCategory.DESSERT, 2.99, 160),
    FRUIT_CUP("Fruit Cup", ItemCategory.DESSERT, 2.49, 60),
    CAKE("Gourmet Cheesecake", ItemCategory.DESSERT, 8.99, 450);
    
    private final String displayName;
    private final ItemCategory category;
    private final double price;
    private final int calories;
    
    MealItemType(String displayName, ItemCategory category, double price, int calories) {
        this.displayName = displayName;
        this.category = category;
        this.price = price;
        this.calories = calories;
    }
    
    /**
     * Gets the human-readable display name for this meal item.
     * 
     * @return The display name
     */
    public String getDisplayName() {
        return displayName;
    }
    
    /**
     * Gets the category this meal item belongs to.
     * 
     * @return The item category
     */
    public ItemCategory getCategory() {
        return category;
    }
    
    /**
     * Gets the price of this meal item in USD.
     * 
     * @return The price
     */
    public double getPrice() {
        return price;
    }
    
    /**
     * Gets the calorie content of this meal item.
     * 
     * @return The calorie count
     */
    public int getCalories() {
        return calories;
    }
    
    /**
     * Checks if this item is vegetarian-friendly.
     * 
     * @return true if vegetarian, false otherwise
     */
    public boolean isVegetarian() {
        return this == VEGGIE_BURGER || this == FRENCH_FRIES || this == ONION_RINGS || 
               this == COLESLAW || this == GARDEN_SALAD || this == MOZZARELLA_STICKS ||
               this.category == ItemCategory.DRINK || this.category == ItemCategory.DESSERT;
    }
    
    /**
     * Checks if this item is considered healthy (under 300 calories).
     * 
     * @return true if healthy, false otherwise
     */
    public boolean isHealthy() {
        return calories < 300;
    }
    
    @Override
    public String toString() {
        return String.format("%s ($%.2f, %d cal)", displayName, price, calories);
    }
}
