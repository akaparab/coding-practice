package com.example.demo.karat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PlayerZeroOrOneLostGame {
    public List<List<Integer>> findWinners(int[][] matches) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> reswinners = new ArrayList<>();
        Set<Integer> winnners = Arrays.stream(matches).map(x -> x[0]).collect(Collectors.toSet());
        List<Integer> loosers = Arrays.stream(matches).map(x -> x[1]).toList();

        for (Integer win : winnners) {
            if (!loosers.contains(win)) {
                reswinners.add(win);
            }
        }
        Map<Integer, Long> countMap = loosers.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        List<Integer> keysWithValueOne = countMap.entrySet().stream()
                .filter(entry -> Objects.equals(entry.getValue(), 1L))
                .map(Map.Entry::getKey)
                .toList();


        res.add(reswinners);
        res.add(keysWithValueOne);

        return res;

    }

    public static void main(String[] args) {
        int[][] players = {{1, 3}, {2, 3}, {3, 6}, {5, 6}, {5, 7}, {4, 5}, {4, 8}, {4, 9}, {10, 4}, {10, 9}};
        PlayerZeroOrOneLostGame pg = new PlayerZeroOrOneLostGame();
        List<List<Integer>> lists = pg.findWinners(players);
        for (List<Integer> list : lists) {
            System.out.println(list.toString());
        }
    }
}
