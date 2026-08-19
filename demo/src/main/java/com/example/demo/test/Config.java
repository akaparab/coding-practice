package com.example.demo.test;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Config {
    private static volatile Config instance;
    private String environment;

    private Config() {
    }

    public static Config getInstance() {

        if (instance == null) {

            synchronized (Config.class) {

                if (instance == null) {

                    instance = new Config();

                }

            }

        }
        return instance;

    }

    public void setEnvironment(String envStr) {
        this.environment = envStr;
    }

    public String getEnvironment() {
        return environment;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Thread t1 = new Thread(() -> {
                Config config = Config.getInstance();
                config.setEnvironment("DEV");
                System.out.println(config.getEnvironment());
            });
            t1.start();
            System.out.println("Thread 1 : " + t1.getName());
        }
    }
}
