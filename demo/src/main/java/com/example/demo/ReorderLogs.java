package com.example.demo;

import java.util.*;

public class ReorderLogs {

    public String[] reorderLogFiles(String[] logs) {

        Arrays.sort(logs, (log1, log2) -> {

            String[] split1 = log1.split(" ", 2);
            String[] split2 = log2.split(" ", 2);

            String identifier1 = split1[0];
            String identifier2 = split2[0];

            String content1 = split1[1];
            String content2 = split2[1];

            boolean isDigit1 = Character.isDigit(content1.charAt(0));
            boolean isDigit2 = Character.isDigit(content2.charAt(0));

            // Both are letter logs
            if (!isDigit1 && !isDigit2) {
                int compareContent = content1.compareTo(content2);

                // If contents are same, compare identifiers
                if (compareContent == 0) {
                    return identifier1.compareTo(identifier2);
                }

                return compareContent;
            }

            // Letter log comes before digit log
            if (!isDigit1 && isDigit2) {
                return -1;
            }

            // Digit log comes after letter log
            if (isDigit1 && !isDigit2) {
                return 1;
            }

            // Both are digit logs
            // Return 0 to preserve original order (stable sort)
            return 0;
        });

        return logs;
    }

    public Deque<String> reorderLogs(List<String> logs) {
        Map<String, String> logMap = new TreeMap<>();
        Set<String> contentSet = new TreeSet<>();

        Set<String> digitalSet = new TreeSet<>();


        for (String log : logs) {
            int idx = log.indexOf(" ");

            String key = log.substring(0, idx);
            String val = log.substring(idx + 1);
            if (val.matches("\\d+")) {
                digitalSet.add(log);
            } else {
                logMap.put(val, key);
                contentSet.add(val);
            }

        }

        Deque<String> res = new LinkedList<>();
        for (String log : contentSet) {
            res.addFirst(logMap.get(log) + " " + log);

        }
        res.addAll(digitalSet);

        return res;
    }

    public static void main(String[] args) {
        List<String> logs = List.of("dig1 8 1 5 1",
                "let1 art can",
                "dig2 3 6",
                "let2 own kit dig",
                "let3 art zero");
        ;

        ReorderLogs rl = new ReorderLogs();
        System.out.println(rl.reorderLogs(logs).toString());
        String[] result = rl.reorderLogFiles(logs.toArray(String[]::new));

        for (String log : result) {
            System.out.println(log);
        }
    }
}
