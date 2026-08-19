package com.example.demo.test;

/**
 * Which singleton implementation do you prefer?", a strong answer is:
 * <p>
 * "For most applications, I prefer an enum singleton because it's thread-safe by design,
 * serialization-safe, and resistant to reflection attacks without requiring synchronization
 * or volatile. If lazy initialization with more control or dependency injection is needed,
 * I might use the Initialization-on-Demand Holder Idiom instead. I generally avoid implementing
 * Double-Checked Locking unless there's a specific reason, because it's more complex and easier
 * to get wrong."
 */

public enum Config1 {

    INSTANCE;

    // Fields
    private String environment = "PROD";

    // Methods
    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }


    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Thread t1 = new Thread(() -> {
                Config1 config = Config1.INSTANCE;
                config.setEnvironment("DEV");
                System.out.println(config.getEnvironment());
            });
            t1.start();
            System.out.println("Thread 1 : " + t1.getName());
        }

    }
}
