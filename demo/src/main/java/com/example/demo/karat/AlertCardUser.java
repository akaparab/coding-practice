package com.example.demo.karat;

import com.example.demo.ratelimitter.SlidingWindowCounterRateLimitter;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;


/**
 * LeetCode company workers use key-cards to unlock office doors. Each time a worker uses their key-card, the security system saves the worker's name and the time when it was used. The system emits an alert if any worker uses the key-card three or more times in a one-hour period.
 * <p>
 * You are given a list of strings keyName and keyTime where [keyName[i], keyTime[i]] corresponds to a person's name and the time when their key-card was used in a single day.
 */
public class AlertCardUser {

    public List<String> alertNames1(String[] keyName, String[] keyTime) {
        Map<String, List<Integer>> map = new HashMap<>();
        List<String> names = new ArrayList<>();
        for (int i = 0; i < keyName.length; i++) {
            map.computeIfAbsent(keyName[i], k -> new ArrayList<>()).add(convertToMins(keyTime[i]));
        }


        for (Map.Entry<String, List<Integer>> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
            List<Integer> values = entry.getValue();
            for (int i = 2; i < values.size(); i++) {
                if (values.get(i) - values.get(0) <= 60) {
                    names.add(entry.getKey());
                }
            }

        }

        return names;

    }

    public List<String> alertNames(String[] keyName, String[] keyTime) {
        Map<String, TreeSet<Integer>> nameToTime = new HashMap<>();
        for (int i = 0; i < keyName.length; ++i) {
            int time = Integer.parseInt(keyTime[i].substring(0, 2)) * 60 + Integer.parseInt(keyTime[i].substring(3));
            nameToTime.computeIfAbsent(keyName[i], s -> new TreeSet<>()).add(time);
        }
        TreeSet<String> names = new TreeSet<>();
        for (Map.Entry<String, TreeSet<Integer>> e : nameToTime.entrySet()) {
            Deque<Integer> dq = new ArrayDeque<>();
            for (int time : e.getValue()) {
                dq.offer(time);
                if (dq.peekLast() - dq.peek() > 60) {
                    dq.poll();
                }
                if (dq.size() >= 3) {
                    names.add(e.getKey());
                    break;
                }
            }
        }
        return new ArrayList<>(names);
    }

    private int convertToMins(String time) {
        String[] numStrs = time.split(":");
        int num = Integer.parseInt(numStrs[0]) * 60;
        num += Integer.parseInt(numStrs[1]);
        return num;
    }


    public static void main(String[] args) {
        String[] keyNames = {"daniel", "daniel", "daniel", "luis", "luis", "luis", "luis"};
        String[] keyTime = {"10:00", "10:40", "11:00", "09:00", "11:00", "13:00", "15:00"};
        AlertCardUser acu = new AlertCardUser();
        List<String> names = acu.alertNames(keyNames, keyTime);
        System.out.println(names.toString());
    }

}
