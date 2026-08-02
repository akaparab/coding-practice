package com.example.demo.designpatterns;

public class ConfigurationManager {

    private static final ConfigurationManager INSTANCE = new ConfigurationManager();

    private ConfigurationManager() {
    }

    public static ConfigurationManager getInstance() {
        if (INSTANCE != null) {
            return new ConfigurationManager();
        }
        return null;
    }
}
