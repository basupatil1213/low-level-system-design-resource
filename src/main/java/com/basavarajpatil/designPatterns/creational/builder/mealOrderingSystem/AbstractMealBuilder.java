package com.basavarajpatil.designPatterns.creational.builder.mealOrderingSystem;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract base class for meal builders providing common functionality.
 * This class implements the template method pattern alongside the builder pattern,
 * providing a consistent structure for all concrete meal builders.
 * 
 * @author Basavaraj Patil
 * @version 1.0
 */
public abstract class AbstractMealBuilder implements MealBuilder {
    
    protected Meal meal;
    protected final MealType mealType;
    protected final List<String> buildLog;
    
    /**
     * Creates a new abstract meal builder for the specified meal type.
     * 
     * @param mealType The type of meal this builder creates
     */
    protected AbstractMealBuilder(MealType mealType) {
        this.mealType = mealType;
        this.buildLog = new ArrayList<>();
    }
    
    @Override
    public MealBuilder reset(String orderId, String customerName) {
        this.meal = new Meal(orderId, customerName, mealType);
        this.buildLog.clear();
        this.buildLog.add("Meal builder reset for " + mealType.getDisplayName());
        return this;
    }
    
    @Override
    public MealBuilder buildMainItem() {
        if (meal == null) {
            throw new IllegalStateException("Builder must be reset before building items");
        }
        
        MealItem mainItem = selectMainItem();
        if (mainItem != null) {
            meal.addItem(mainItem);
            buildLog.add("Added main item: " + mainItem.getName());
        }
        return this;
    }
    
    @Override
    public MealBuilder buildSide() {
        if (meal == null) {
            throw new IllegalStateException("Builder must be reset before building items");
        }
        
        MealItem sideItem = selectSideItem();
        if (sideItem != null) {
            meal.addItem(sideItem);
            buildLog.add("Added side item: " + sideItem.getName());
        }
        return this;
    }
    
    @Override
    public MealBuilder buildDrink() {
        if (meal == null) {
            throw new IllegalStateException("Builder must be reset before building items");
        }
        
        MealItem drinkItem = selectDrinkItem();
        if (drinkItem != null) {
            meal.addItem(drinkItem);
            buildLog.add("Added drink: " + drinkItem.getName());
        }
        return this;
    }
    
    @Override
    public MealBuilder buildDessert() {
        if (meal == null) {
            throw new IllegalStateException("Builder must be reset before building items");
        }
        
        MealItem dessertItem = selectDessertItem();
        if (dessertItem != null) {
            meal.addItem(dessertItem);
            buildLog.add("Added dessert: " + dessertItem.getName());
        } else {
            buildLog.add("No dessert selected");
        }
        return this;
    }
    
    @Override
    public MealBuilder addSpecialInstruction(String instruction) {
        if (meal == null) {
            throw new IllegalStateException("Builder must be reset before adding instructions");
        }
        
        meal.addSpecialInstruction(instruction);
        buildLog.add("Added special instruction: " + instruction);
        return this;
    }
    
    @Override
    public boolean validate() {
        if (meal == null) {
            return false;
        }
        
        // Check for essential items based on meal type
        if (!meal.isComplete()) {
            buildLog.add("Validation failed: Missing essential items - " + 
                       meal.getMissingEssentialItems());
            return false;
        }
        
        // Meal type specific validation
        if (!validateMealTypeRequirements()) {
            return false;
        }
        
        buildLog.add("Validation passed");
        return true;
    }
    
    @Override
    public MealType getMealType() {
        return mealType;
    }
    
    @Override
    public Meal getMeal() {
        if (meal == null) {
            throw new IllegalStateException("No meal has been built. Call reset() first.");
        }
        
        if (!validate()) {
            throw new IllegalStateException("Cannot return invalid meal. Check validation errors: " + 
                                          String.join(", ", getValidationErrors()));
        }
        
        buildLog.add("Meal construction completed successfully");
        return meal;
    }
    
    @Override
    public String getBuildSummary() {
        StringBuilder summary = new StringBuilder();
        summary.append("Build Summary for ").append(mealType.getDisplayName()).append(":\n");
        
        for (int i = 0; i < buildLog.size(); i++) {
            summary.append(String.format("%2d. %s\n", i + 1, buildLog.get(i)));
        }
        
        if (meal != null) {
            summary.append("\nCurrent Meal State:\n");
            summary.append(String.format("  Items: %d\n", meal.getAllItems().size()));
            summary.append(String.format("  Total Price: $%.2f\n", meal.getTotalPrice()));
            summary.append(String.format("  Total Calories: %d\n", meal.getTotalCalories()));
            summary.append(String.format("  Complete: %s\n", meal.isComplete()));
        }
        
        return summary.toString();
    }
    
    /**
     * Selects the appropriate main item for this meal type.
     * Concrete builders must implement this method.
     * 
     * @return The selected main item, or null if none
     */
    protected abstract MealItem selectMainItem();
    
    /**
     * Selects the appropriate side item for this meal type.
     * Concrete builders must implement this method.
     * 
     * @return The selected side item, or null if none
     */
    protected abstract MealItem selectSideItem();
    
    /**
     * Selects the appropriate drink for this meal type.
     * Concrete builders must implement this method.
     * 
     * @return The selected drink, or null if none
     */
    protected abstract MealItem selectDrinkItem();
    
    /**
     * Selects the appropriate dessert for this meal type.
     * Concrete builders may override this method.
     * 
     * @return The selected dessert, or null if none
     */
    protected MealItem selectDessertItem() {
        // Default implementation - no dessert
        return null;
    }
    
    /**
     * Validates meal type specific requirements.
     * Concrete builders can override this for custom validation.
     * 
     * @return true if meal type requirements are met
     */
    protected boolean validateMealTypeRequirements() {
        // Default validation - just check basic requirements
        return true;
    }
    
    /**
     * Gets a list of current validation errors.
     * 
     * @return List of validation error messages
     */
    protected List<String> getValidationErrors() {
        List<String> errors = new ArrayList<>();
        
        if (meal == null) {
            errors.add("No meal initialized");
            return errors;
        }
        
        if (!meal.isComplete()) {
            errors.add("Missing essential items: " + meal.getMissingEssentialItems());
        }
        
        return errors;
    }
}
