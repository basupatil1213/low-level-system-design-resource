package com.basavarajpatil.designPatterns.creational.builder.mealOrderingSystem;

public class Client {
    public static void main(String[] args) {
        MealBuilder kidsBuilder = new KidsMealBuilder();
        MealDirector director = new MealDirector(kidsBuilder);
        Meal kidsMeal = director.constructMeal();
        System.out.println("Kids Meal:");
        kidsMeal.showItems();

        System.out.println();

        MealBuilder deluxeBuilder = new DeluxeMealBuilder();
        director = new MealDirector(deluxeBuilder);
        Meal deluxeMeal = director.constructMeal();
        System.out.println("Deluxe Meal:");
        deluxeMeal.showItems();
    }
}
