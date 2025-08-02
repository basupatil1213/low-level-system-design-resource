package com.basavarajpatil.designPatterns.creational.builder.mealOrderingSystem;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Represents a complete meal consisting of various meal items.
 * This class is the Product in the Builder pattern, containing all the
 * components that make up a customer's meal order.
 * 
 * @author Basavaraj Patil
 * @version 1.0
 */
public class Meal {
    
    private final String orderId;
    private final String customerName;
    private final LocalDateTime orderTime;
    private final Map<ItemCategory, MealItem> items;
    private final List<String> specialInstructions;
    private final MealType mealType;
    
    /**
     * Package-private constructor for builder pattern.
     * Only builders within the same package can create Meal instances.
     * 
     * @param orderId Unique identifier for this meal order
     * @param customerName Name of the customer
     * @param mealType Type of meal (Kids, Deluxe, etc.)
     */
    Meal(String orderId, String customerName, MealType mealType) {
        this.orderId = Objects.requireNonNull(orderId, "Order ID cannot be null");
        this.customerName = customerName != null ? customerName : "Guest";
        this.orderTime = LocalDateTime.now();
        this.items = new EnumMap<>(ItemCategory.class);
        this.specialInstructions = new ArrayList<>();
        this.mealType = Objects.requireNonNull(mealType, "Meal type cannot be null");
    }
    
    /**
     * Creates a new MealBuilder for the specified meal type.
     * This is the recommended way to create meals following the Builder pattern.
     * 
     * @param mealType The type of meal to build
     * @return A new MealBuilder instance
     */
    public static MealBuilder builder(MealType mealType) {
        switch (mealType) {
            case KIDS:
                return new KidsMealBuilder();
            case DELUXE:
                return new DeluxeMealBuilder();
            default:
                throw new IllegalArgumentException("Unsupported meal type: " + mealType);
        }
    }
    
    /**
     * Adds or replaces a meal item in the specified category.
     * 
     * @param item The meal item to add
     */
    public void addItem(MealItem item) {
        Objects.requireNonNull(item, "Meal item cannot be null");
        items.put(item.getCategory(), item);
    }
    
    /**
     * Gets the meal item for the specified category.
     * 
     * @param category The item category
     * @return The meal item, or null if none exists
     */
    public MealItem getItem(ItemCategory category) {
        return items.get(category);
    }
    
    /**
     * Checks if the meal has an item in the specified category.
     * 
     * @param category The item category to check
     * @return true if item exists, false otherwise
     */
    public boolean hasItem(ItemCategory category) {
        return items.containsKey(category);
    }
    
    /**
     * Gets all meal items as an unmodifiable collection.
     * 
     * @return Collection of all meal items
     */
    public Collection<MealItem> getAllItems() {
        return Collections.unmodifiableCollection(items.values());
    }
    
    /**
     * Adds a special instruction for meal preparation.
     * 
     * @param instruction The special instruction
     */
    public void addSpecialInstruction(String instruction) {
        if (instruction != null && !instruction.trim().isEmpty()) {
            specialInstructions.add(instruction.trim());
        }
    }
    
    /**
     * Gets all special instructions.
     * 
     * @return Unmodifiable list of special instructions
     */
    public List<String> getSpecialInstructions() {
        return Collections.unmodifiableList(specialInstructions);
    }
    
    /**
     * Calculates the total price of all items in the meal.
     * 
     * @return The total price
     */
    public double getTotalPrice() {
        return items.values().stream()
                .mapToDouble(MealItem::getPrice)
                .sum();
    }
    
    /**
     * Calculates the total calorie content of the meal.
     * 
     * @return The total calories
     */
    public int getTotalCalories() {
        return items.values().stream()
                .mapToInt(MealItem::getCalories)
                .sum();
    }
    
    /**
     * Checks if the entire meal is vegetarian-friendly.
     * 
     * @return true if all items are vegetarian, false otherwise
     */
    public boolean isVegetarian() {
        return items.values().stream()
                .allMatch(MealItem::isVegetarian);
    }
    
    /**
     * Checks if the meal is considered healthy (under 800 total calories).
     * 
     * @return true if healthy, false otherwise
     */
    public boolean isHealthy() {
        return getTotalCalories() < 800;
    }
    
    /**
     * Checks if the meal is complete (has all essential items).
     * 
     * @return true if complete, false otherwise
     */
    public boolean isComplete() {
        return Arrays.stream(ItemCategory.values())
                .filter(ItemCategory::isEssential)
                .allMatch(this::hasItem);
    }
    
    /**
     * Gets missing essential items from the meal.
     * 
     * @return List of missing essential categories
     */
    public List<ItemCategory> getMissingEssentialItems() {
        return Arrays.stream(ItemCategory.values())
                .filter(ItemCategory::isEssential)
                .filter(category -> !hasItem(category))
                .collect(Collectors.toList());
    }
    
    /**
     * Displays the meal items in a formatted way.
     */
    public void showItems() {
        System.out.println(generateReceipt());
    }
    
    /**
     * Generates a detailed receipt for the meal.
     * 
     * @return Formatted receipt string
     */
    public String generateReceipt() {
        StringBuilder receipt = new StringBuilder();
        
        // Header
        receipt.append("═══════════════════════════════════════════════\n");
        receipt.append(String.format("           %s MEAL ORDER RECEIPT\n", mealType.name()));
        receipt.append("═══════════════════════════════════════════════\n");
        receipt.append(String.format("Order ID: %s\n", orderId));
        receipt.append(String.format("Customer: %s\n", customerName));
        receipt.append(String.format("Order Time: %s\n", orderTime.toString().replace("T", " ").substring(0, 19)));
        receipt.append("───────────────────────────────────────────────\n");
        
        // Items by category
        for (ItemCategory category : ItemCategory.values()) {
            MealItem item = items.get(category);
            if (item != null) {
                receipt.append(String.format("%-12s: %s\n", 
                        category.getDisplayName(), 
                        item.getFormattedDescription()));
            }
        }
        
        receipt.append("───────────────────────────────────────────────\n");
        
        // Special instructions
        if (!specialInstructions.isEmpty()) {
            receipt.append("Special Instructions:\n");
            for (String instruction : specialInstructions) {
                receipt.append(String.format("  • %s\n", instruction));
            }
            receipt.append("───────────────────────────────────────────────\n");
        }
        
        // Summary
        receipt.append(String.format("Total Items: %d\n", items.size()));
        receipt.append(String.format("Total Calories: %d cal\n", getTotalCalories()));
        receipt.append(String.format("TOTAL PRICE: $%.2f\n", getTotalPrice()));
        
        // Meal characteristics
        List<String> characteristics = new ArrayList<>();
        if (isVegetarian()) characteristics.add("Vegetarian");
        if (isHealthy()) characteristics.add("Healthy Choice");
        if (!isComplete()) characteristics.add("Incomplete Order");
        
        if (!characteristics.isEmpty()) {
            receipt.append(String.format("Meal Tags: %s\n", String.join(", ", characteristics)));
        }
        
        receipt.append("═══════════════════════════════════════════════");
        
        return receipt.toString();
    }
    
    // Getters
    public String getOrderId() { return orderId; }
    public String getCustomerName() { return customerName; }
    public LocalDateTime getOrderTime() { return orderTime; }
    public MealType getMealType() { return mealType; }
    
    @Override
    public String toString() {
        return String.format("Meal{orderId='%s', customer='%s', type=%s, items=%d, total=$%.2f}", 
                orderId, customerName, mealType, items.size(), getTotalPrice());
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meal meal = (Meal) o;
        return Objects.equals(orderId, meal.orderId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(orderId);
    }
}
