package com.basavarajpatil.designPatterns.creational.Singleton.Logger;

public class LoggerBillPughSingleton {

    private LoggerBillPughSingleton(){}

    private static  class LoggerBillPughSingletonHolder{
        private static final LoggerBillPughSingleton instance = new LoggerBillPughSingleton();
    }

    public static LoggerBillPughSingleton getInstance(){
        return LoggerBillPughSingletonHolder.instance;
    }
}
