package com.basavarajpatil.designPatterns.creational.Singleton.Logger;

public class Logger {
    private static volatile Logger logger;

    private Logger() {}

    public static Logger getInstance(){
        if(logger == null){
            synchronized (Logger.class){
                if(logger == null){
                    logger = new Logger();
                }
            }
        }
        return logger;
    }

    public void info(String message ){
        System.out.println("[INFO] " + message);
    }

    public void debug(String message ){
        System.out.println("[DEBUG] " + message);
    }

    public void error(String message ){
        System.out.println("[ERROR] " + message);
    }

    public void warn(String message ){
        System.out.println("[WARN] " + message);
    }


}
