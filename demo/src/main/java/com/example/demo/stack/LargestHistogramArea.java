package com.example.demo.stack;

import java.util.Stack;

public class LargestHistogramArea {

    public int largestRectangleArea(int[] heights) {

        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        int n = heights.length;

        // Traverse all bars plus one extra iteration
        for (int i = 0; i <= n; i++) {

            int currentHeight = (i == n) ? 0 : heights[i];

            while (!stack.isEmpty() &&
                    currentHeight < heights[stack.peek()]) {

                int height = heights[stack.pop()];

                int width;

                if (stack.isEmpty()) {
                    width = i;
                } else {
                    width = i - stack.peek() - 1;
                }

                maxArea = Math.max(maxArea, height * width);
            }

            stack.push(i);
        }

        return maxArea;
    }

    public static void main(String[] args) {

        LargestHistogramArea obj = new LargestHistogramArea();

        int[] heights = {2, 1, 5, 6, 2, 3};

        System.out.println(obj.largestRectangleArea(heights)); // 10
    }
}
