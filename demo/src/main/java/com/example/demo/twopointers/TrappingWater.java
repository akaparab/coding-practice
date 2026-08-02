package com.example.demo.twopointers;

public class TrappingWater {
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

    public int trappingWater(int[] heights) {
        if (heights.length == 0) {
            return 0;
        }
        int left = 0, right = heights.length - 1;
        int leftMax = heights[left], rightMax = heights[right];
        int count = 0;

        while (left < right) {
            if (leftMax < rightMax) {
                left++;
                if (heights[left] >= leftMax) {
                    leftMax = heights[left];
                } else {
                    count += leftMax - heights[left];
                }
            } else {
                right--;
                if (heights[right] >= rightMax) {
                    rightMax = heights[right];
                } else {
                    count += rightMax - heights[right];
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int[] nums = {3, 4, 1, 2, 2, 5, 1, 0, 2};
        TrappingWater tw = new TrappingWater();
        System.out.println(tw.trappingWater(nums));
        System.out.println(tw.max_area(nums));

    }
}
