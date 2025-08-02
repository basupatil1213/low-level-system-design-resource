package com.basavarajpatil.designPatterns.creational.builder.mealOrderingSystem;

/**
 * Client class demonstrating the Builder pattern for meal ordering system.
 * Shows different ways to construct meals using various builder approaches.
 * 
 * @author Basavaraj Patil
 * @version 1.0
 */
public class Client {
    
    public static void main(String[] args) {
        System.out.println("🍽️ Meal Ordering System - Builder Pattern Demo");
        System.out.println("================================================");
        
        // Demonstrate different builder usage patterns
        demonstrateDirectorPattern();
        demonstrateFluentBuilder();
        demonstrateStaticBuilderFactory();
        demonstrateComplexMealBuilding();
        demonstrateValidationAndErrorHandling();
        
        System.out.println("\n🎯 Builder Pattern Demo Completed!");
        System.out.println("===================================");
    }
    
    /**
     * Demonstrates the classic Director pattern approach.
     */
    private static void demonstrateDirectorPattern() {
        System.out.println("\n1️⃣ DIRECTOR PATTERN APPROACH");
        System.out.println("------------------------------");
        
        // Kids meal using director
        MealBuilder kidsBuilder = new KidsMealBuilder();
        MealDirector director = new MealDirector(kidsBuilder);
        Meal kidsMeal = director.constructMeal("ORD-001", "Emma Johnson");
        
        System.out.println("👶 Kids Meal (Director Pattern):");
        kidsMeal.showItems();
        
        System.out.println("\n" + "=".repeat(50));
        
        // Deluxe meal using director
        MealBuilder deluxeBuilder = new DeluxeMealBuilder();
        director = new MealDirector(deluxeBuilder);
        Meal deluxeMeal = director.constructMeal("ORD-002", "Michael Smith");
        
        System.out.println("\n🍖 Deluxe Meal (Director Pattern):");
        deluxeMeal.showItems();
    }
    
    /**
     * Demonstrates fluent builder interface usage.
     */
    private static void demonstrateFluentBuilder() {
        System.out.println("\n\n2️⃣ FLUENT BUILDER INTERFACE");
        System.out.println("-----------------------------");
        
        // Custom kids meal with fluent interface
        Meal customKidsMeal = new KidsMealBuilder()
                .reset("ORD-003", "Sophia Davis")
                .buildMainItem()
                .buildSide()
                .buildDrink()
                .buildDessert()
                .addSpecialInstruction("No pickles please")
                .addSpecialInstruction("Extra ketchup")
                .getMeal();
        
        System.out.println("👨‍🍳 Custom Kids Meal (Fluent Interface):");
        customKidsMeal.showItems();
        
        System.out.println("\n" + "=".repeat(50));
        
        // Custom deluxe meal with special requirements
        Meal customDeluxeMeal = new DeluxeMealBuilder()
                .reset("ORD-004", "James Wilson")
                .buildMainItem()
                .buildSide()
                .buildDrink()
                .buildDessert()
                .addSpecialInstruction("Medium-rare steak")
                .addSpecialInstruction("Dressing on the side")
                .addSpecialInstruction("No ice in drink")
                .getMeal();
        
        System.out.println("\n🥩 Custom Deluxe Meal (Fluent Interface):");
        customDeluxeMeal.showItems();
    }
    
    /**
     * Demonstrates the static builder factory method.
     */
    private static void demonstrateStaticBuilderFactory() {
        System.out.println("\n\n3️⃣ STATIC BUILDER FACTORY");
        System.out.println("--------------------------");
        
        // Using static factory method for kids meal
        Meal factoryKidsMeal = Meal.builder(MealType.KIDS)
                .reset("ORD-005", "Oliver Brown")
                .buildMainItem()
                .buildSide()
                .buildDrink()
                .addSpecialInstruction("Cut food into small pieces")
                .getMeal();
        
        System.out.println("🏭 Factory Kids Meal:");
        factoryKidsMeal.showItems();
        
        System.out.println("\n" + "=".repeat(50));
        
        // Using static factory method for deluxe meal
        Meal factoryDeluxeMeal = Meal.builder(MealType.DELUXE)
                .reset("ORD-006", "Isabella Garcia")
                .buildMainItem()
                .buildSide()
                .buildDrink()
                .buildDessert()
                .addSpecialInstruction("Gluten-free options preferred")
                .getMeal();
        
        System.out.println("\n🍰 Factory Deluxe Meal:");
        factoryDeluxeMeal.showItems();
    }
    
    /**
     * Demonstrates complex meal building with partial construction.
     */
    private static void demonstrateComplexMealBuilding() {
        System.out.println("\n\n4️⃣ COMPLEX MEAL BUILDING");
        System.out.println("-------------------------");
        
        // Step-by-step meal building
        MealBuilder builder = new DeluxeMealBuilder();
        builder.reset("ORD-007", "Alexander Lee");
        
        System.out.println("🔧 Building meal step by step:");
        System.out.println("Step 1: Adding main item...");
        builder.buildMainItem();
        
        System.out.println("Step 2: Adding side item...");
        builder.buildSide();
        
        System.out.println("Step 3: Adding drink...");
        builder.buildDrink();
        
        System.out.println("Step 4: Adding special instructions...");
        builder.addSpecialInstruction("Extra spicy")
                .addSpecialInstruction("Large portion");
        
        System.out.println("Step 5: Building final meal...");
        Meal stepByStepMeal = builder.getMeal();
        
        System.out.println("\n📋 Build Process Summary:");
        System.out.println(builder.getBuildSummary());
        
        System.out.println("\n🍽️ Final Step-by-Step Meal:");
        stepByStepMeal.showItems();
    }
    
    /**
     * Demonstrates validation and error handling scenarios.
     */
    private static void demonstrateValidationAndErrorHandling() {
        System.out.println("\n\n5️⃣ VALIDATION & ERROR HANDLING");
        System.out.println("--------------------------------");
        
        // Test incomplete meal
        System.out.println("🧪 Testing incomplete meal:");
        Meal incompleteMeal = new KidsMealBuilder()
                .reset("ORD-008", "Mia Martinez")
                .buildMainItem()
                .buildDrink()  // Missing side item and dessert
                .getMeal();
        
        System.out.println("Meal complete: " + incompleteMeal.isComplete());
        System.out.println("Missing items: " + incompleteMeal.getMissingEssentialItems());
        incompleteMeal.showItems();
        
        System.out.println("\n" + "=".repeat(50));
        
        // Test meal characteristics
        System.out.println("\n🔍 Meal Analysis:");
        Meal analysisKidsMeal = new KidsMealBuilder()
                .reset("ORD-009", "Noah Rodriguez")
                .buildMainItem()
                .buildSide()
                .buildDrink()
                .buildDessert()
                .getMeal();
        
        System.out.println("Analysis Results:");
        System.out.println("  • Is Complete: " + analysisKidsMeal.isComplete());
        System.out.println("  • Is Vegetarian: " + analysisKidsMeal.isVegetarian());
        System.out.println("  • Is Healthy: " + analysisKidsMeal.isHealthy());
        System.out.println("  • Total Calories: " + analysisKidsMeal.getTotalCalories());
        System.out.println("  • Total Price: $" + String.format("%.2f", analysisKidsMeal.getTotalPrice()));
        
        analysisKidsMeal.showItems();
        
        // Test error handling
        System.out.println("\n⚠️ Error Handling Test:");
        try {
            MealBuilder errorBuilder = new DeluxeMealBuilder();
            // Try to build without reset (should fail)
            errorBuilder.buildMainItem();
        } catch (IllegalStateException e) {
            System.out.println("Expected error caught: " + e.getMessage());
        }
    }
}
