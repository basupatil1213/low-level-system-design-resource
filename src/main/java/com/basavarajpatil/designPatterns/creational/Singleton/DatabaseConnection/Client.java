package com.basavarajpatil.designPatterns.creational.Singleton.DatabaseConnection;

public class Client {
    public static void main(String[] args) {
        DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
        databaseConnection.disconnect();
        databaseConnection.query("SELECT * FROM users");
        databaseConnection.connect("http:localhost:8080");
    }
}
