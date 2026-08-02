package com.example.demo.ratelimitter;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Getter
@Setter
public class LogRateLimitter {
    public static final Integer window = 10;
    Map<String, Integer> logMap;

    LogRateLimitter() {
        logMap = new ConcurrentHashMap<String, Integer>();
    }

    boolean shouldPrintMessage(int timestamp, String message) {
        if (message == null) return false;
        Iterator<Map.Entry<String, Integer>> iterator = logMap.entrySet().iterator();
        while (iterator.hasNext()) {
            if (timestamp - iterator.next().getValue() >= 10) {
                iterator.remove();
            } else {
                break;
            }
        }

        Integer ts = logMap.get(message);
        if (ts != null && timestamp - ts < 10) {
            return false;
        }

        logMap.put(message, timestamp);
        return true;
    }

    public static void main(String[] args) {
        List<LogMessage> dataList = List.of(
                new LogMessage(null, null),
                new LogMessage(1, "foo"),
                new LogMessage(2, "bar"),
                new LogMessage(3, "foo"),
                new LogMessage(8, "bar"),
                new LogMessage(10, "foo"),
                new LogMessage(11, "foo")
        );

        LogRateLimitter lrl = new LogRateLimitter();

        dataList.forEach(msg -> {
            // If message is null, set flag to false automatically, otherwise check the rate limiter
            boolean flag = (msg.getMessage() == null)
                    ? false
                    : lrl.shouldPrintMessage(msg.getTimeStamp(), msg.getMessage());

            System.out.printf("Message=%s, flag=%s%n", msg, flag);
        });

    }

    @Data
    @RequiredArgsConstructor
    static
    class LogMessage {
        Integer timeStamp;
        String message;

        LogMessage(Integer timeStamp, String message) {
            this.timeStamp = timeStamp;
            this.message = message;
        }

    }


}
