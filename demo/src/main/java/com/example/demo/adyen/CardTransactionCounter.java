package com.example.demo.adyen;

import java.util.Calendar;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

public class CardTransactionCounter {
    ConcurrentHashMap<String, ConcurrentLinkedDeque<Long>> counterMap = new ConcurrentHashMap<>();


    public int getCardTransaction(String cardNumber) {
        long now = System.currentTimeMillis();
        ConcurrentLinkedDeque<Long> queue = counterMap.get(cardNumber);
        cleanup(queue, now);


        return queue.size();
    }

    public void cleanup(ConcurrentLinkedDeque<Long> queue, long time) {
        if (!queue.isEmpty()) {
            if (time - queue.peekFirst() > 10000) {
                queue.poll();
            }
        }
    }

    public void addCardTransactions(String card) {
        long now = System.currentTimeMillis();
        ConcurrentLinkedDeque<Long> queue = counterMap.computeIfAbsent(card, key -> new ConcurrentLinkedDeque<>());
        cleanup(queue, now);
        queue.offerLast(now);
    }

    public static void main(String[] args) throws InterruptedException {
        CardTransactionCounter counter =
                new CardTransactionCounter();

        counter.addCardTransactions("4111111111111111");
        counter.addCardTransactions("4111111111111111");

        System.out.println(
                counter.getCardTransaction(
                        "4111111111111111")); // 2

        Thread.sleep(11000);

        System.out.println(
                counter.getCardTransaction(
                        "4111111111111111")); // 0
    }


}
