package com.basavarajpatil.designPatterns.creational.builder.mealOrderingSystem;

/**
 * Concrete builder for creating kid-friendly meals.
 * This builder creates meals specifically designed for children with
 * smaller portions, kid-approved items, and healthier options when possible.
 * 
 * @author Basavaraj Patil
 * @version 1.0
 */
public class KidsMealBuilder extends AbstractMealBuilder {
    
    /**
     * Creates a new kids meal builder.
     */
    public KidsMealBuilder() {
        super(MealType.KIDS);
    }
    
    @Override
    protected MealItem selectMainItem() {
        // Kids prefer simple, familiar items - choose burger as default
        return new MealItem(MealItemType.BURGER, "Kid-sized portion", -2.00);
    }
    
    @Override
    protected MealItem selectSideItem() {
        // French fries are popular with kids
        return new MealItem(MealItemType.FRENCH_FRIES, "Small portion", -1.00);
    }
    
    @Override
    protected MealItem selectDrinkItem() {
        // Orange juice is healthier for kids than soda
        return new MealItem(MealItemType.ORANGE_JUICE, "Kid-sized cup", -0.50);
    }
    
    @Override
    protected MealItem selectDessertItem() {
        // Kids love ice cream, but keep it simple
        return new MealItem(MealItemType.ICE_CREAM, "Single scoop vanilla", -1.00);
    }
    
    @Override
    protected boolean validateMealTypeRequirements() {
        if (meal == null) {
            return false;
        }
        
        // Kids meals should be under 600 calories
        if (meal.getTotalCalories() > 600) {
            buildLog.add("Validation warning: Kids meal exceeds 600 calories (" + 
                        meal.getTotalCalories() + " cal)");
        }
        
        // Kids meals should include a dessert for the "happy meal" experience
        if (!meal.hasItem(ItemCategory.DESSERT)) {
            buildLog.add("Validation note: Kids meal without dessert");
        }
        
        // Price should be reasonable for kids meal
        if (meal.getTotalPrice() > 8.00) {
            buildLog.add("Validation warning: Kids meal price exceeds $8.00");
        }
        
        return true; // These are warnings, not blocking errors
    }
    
    /**
     * Creates a healthy alternative kids meal with more nutritious options.
     * 
     * @return This builder configured for healthy options
     */
    public KidsMealBuilder makeHealthy() {
        if (meal != null) {
            // Replace items with healthier alternatives
            meal.addItem(new MealItem(MealItemType.GRILLED_CHICKEN, "Kid-sized, no seasoning", -3.00));
            meal.addItem(new MealItem(MealItemType.GARDEN_SALAD, "Kid-friendly with ranch", -2.00));
            meal.addItem(new MealItem(MealItemType.WATER, "Flavored water", 0.00));
            meal.addItem(new MealItem(MealItemType.FRUIT_CUP, "Mixed fresh fruits", 0.00));
            
            buildLog.add("Configured as healthy kids meal");
        }
        return this;
    }
    
    /**
     * Adds kid-friendly customizations to make the meal more appealing.
     * 
     * @return This builder with fun customizations
     */
    public KidsMealBuilder makeFun() {
        if (meal != null) {
            meal.addSpecialInstruction("Cut sandwich into fun shapes");
            meal.addSpecialInstruction("Include colorful straws");
            meal.addSpecialInstruction("Add extra napkins for messy eating");
            
            buildLog.add("Added fun customizations for kids");
        }
        return this;
    }
}
