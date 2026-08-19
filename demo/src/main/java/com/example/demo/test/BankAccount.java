package com.example.demo.test;

import java.util.concurrent.atomic.AtomicInteger;

public class BankAccount {
    private final AtomicInteger balance = new AtomicInteger();

    public void deposit(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        balance.addAndGet(amount);
    }

    public boolean withdraw(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }

        while (true) {
            int current = balance.get();

            if (current < amount) {
                return false;
            }

            if (balance.compareAndSet(current, current - amount)) {
                return true;
            }
        }
    }

    public int getBalance() {
        return balance.get();
    }

    public static void main(String[] args) throws Exception {

        BankAccount account = new BankAccount();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++)
                account.deposit(1);
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++)
                account.withdraw(1);
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(account.getBalance());
    }
}
