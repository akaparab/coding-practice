package com.example.demo.stack;

import java.util.Arrays;
import java.util.Stack;

public class MinStack {
    private Stack<int[]> st;

    public MinStack() {
        st = new Stack<>();
    }

    public void push(int value) {
        if(st.isEmpty()) {
            st.push(new int[] {value, value});
        }

        int[] cur = st.peek();
        int min = Math.min(value, cur[1]);
        st.push(new int[]{value, min});
    }

    public void pop() {
        if(!st.isEmpty())
           st.pop();
    }

    public int top() {
        int[] top = st.peek();
        return top[1];
    }

    public int getMin() {
        return top();
    }
}
