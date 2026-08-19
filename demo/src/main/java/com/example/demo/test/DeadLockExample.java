package com.example.demo.test;

public class DeadLockExample {
    private final Object lockA = new Object();
    private final Object lockB = new Object();

    public void transferAB() {

        synchronized (lockA) {

            sleep();

            synchronized (lockB) {

                System.out.println("A -> B");

            }
        }
    }

    public void transferBA() {

        synchronized (lockB) {

            sleep();

            synchronized (lockA) {

                System.out.println("B -> A");

            }
        }
    }

    public void transferBADeadLockFix() {

        synchronized (lockA) {

            sleep();

            synchronized (lockB) {

                System.out.println("B -> A");

            }
        }
    }

    private void sleep() {

        try {
            Thread.sleep(100);
        } catch (Exception e) {

        }
    }

    public static void main(String[] args) {

        DeadLockExample d = new DeadLockExample();

        new Thread(d::transferAB).start();

        new Thread(d::transferBA).start();
    }
}

