package com.example.demo.slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class FruitBasket {
    // Add only pick 2 types of fruits into basket
    public int fruitIntoBaskets(int[] fruits) {
        if (fruits.length == 0) return 0;

        Map<Integer, Integer> map = new HashMap<>();
        int start = 0;
        int k = 2;
        int maxFruit = 0;

        for (int i = 0; i < fruits.length; i++) {
            map.put(fruits[i], map.getOrDefault(fruits[i], 0) + 1);

            while (map.size() > k) {
                map.put(fruits[start], map.get(fruits[start]) - 1);

                if (map.get(fruits[start]) == 0) {
                    map.remove(fruits[start]);

                }
                start++;
            }
            maxFruit = Math.max(maxFruit, i - start + 1);
        }
        return maxFruit;


    }

    public static void main(String[] args) {
        int[] fruits = {3, 3, 2, 1, 2, 1, 0};
        FruitBasket fb = new FruitBasket();
        System.out.println(fb.fruitIntoBaskets(fruits));

    }
}
