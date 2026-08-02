package com.example.demo.twopointers;

public class ContainerWater {
    public Integer max_area(int[] heights) {
        int i = 0;
        int j = heights.length - 1;
        int maxArea = 0;

        while (i < j) {

            int lowHeight = Math.min(heights[i], heights[j]);
            maxArea = Math.max(maxArea, maxArea = lowHeight * (j - i));

            if (heights[i] < heights[j]) {
                i++;
            } else if (heights[i] > heights[j]) {
                j--;
            } else {
                i++;
                j--;
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {
        int[] nums = {3, 4, 1, 2, 2, 4, 1, 3, 2};
        ContainerWater cw = new ContainerWater();

        System.out.println(cw.max_area(nums));
    }
}
