package com.example.demo.stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class DailyTemperature {
    public int[] dailyTemperatures(int[] temperatures) {
        Deque<Integer> stack = new ArrayDeque<>();
        int[] ans = new int[temperatures.length];
        for (int i = 0; i < temperatures.length; i++) {
            int curTemp = temperatures[i];

            while (!stack.isEmpty() && temperatures[stack.peek()] < curTemp) {
                int idx = stack.pop();
                ans[idx] = i - idx;
            }
            stack.push(i);
        }
        return ans;


    }

    public static void main(String[] args) {
        int[] temp = {73, 74, 75, 71, 69, 72, 76, 73};
        DailyTemperature dt = new DailyTemperature();
        int[] res = dt.dailyTemperatures(temp);
        System.out.println(Arrays.toString(res));
    }
}
