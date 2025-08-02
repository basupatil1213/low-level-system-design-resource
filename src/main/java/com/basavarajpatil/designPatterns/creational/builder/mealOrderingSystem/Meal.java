package com.basavarajpatil.designPatterns.creational.builder.mealOrderingSystem;

public class Meal {
    private String MainItem;
    private String side;
    private String drink;
    private String dessert;

    public void setMainItem(String mainItem) {
        this.MainItem = mainItem;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public void setDrink(String drink) {
        this.drink = drink;
    }

    public void setDessert(String dessert) {
        this.dessert = dessert;
    }

    public void showItems(){
        if(this.MainItem != null)
            System.out.println("Main Item: " + this.MainItem);
        if (this.side != null)
            System.out.println("Side: " + this.side);
        if (this.drink != null)
            System.out.println("Drink: " + this.drink);
        if (this.dessert != null)
            System.out.println("Dessert: " + this.dessert);
    }

}
