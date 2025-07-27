package com.basavarajpatil.designPatterns.creational.Singleton.DatabaseConnection;

public class DatabaseConnection {
    private boolean connected = false;
    private DatabaseConnection() {}

    private static class DatabaseConnectionHolder {
        private static final DatabaseConnection INSTANCE = new DatabaseConnection();
    }

    public static DatabaseConnection getInstance(){
        return DatabaseConnectionHolder.INSTANCE;
    }

    public void connect(String connectionString){
        if(connected){
            System.out.println("Database connection is already established");
            return;
        }
        System.out.println("Connecting to Database.....");
        System.out.println("connection string: " + connectionString);
        System.out.println("Successfully connected to Database..!");
    }

    public void disconnect(){
        if(!connected){
            System.out.println("Database connection is not established");
            return;
        }
        System.out.println("Disconnecting from Database.....");
        System.out.println("Successfully disconnected from Database..!");
    }

    public void query(String query){
        if(!connected){
            System.out.println("Database connection is not established");
            return;
        }
        System.out.println("Executing query: " + query);
        System.out.println("Successfully executed..!");
    }
}
