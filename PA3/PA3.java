package edu.neu.megn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PA3 {
    private static List<List<String>> allConstruct(String target, String[] wordBank, Map<String, List<List<String>>> memo) {
        if (memo.containsKey(target)) {
            return memo.get(target);
        }
        if (target.isEmpty()) {
            List<List<String>> emptyList = new ArrayList<>();
            emptyList.add(new ArrayList<>());
            return emptyList;
        }

        List<List<String>> totalWays = new ArrayList<>();

        for (String word : wordBank) {
            if (target.startsWith(word)) {
                String suffix = target.substring(word.length());
                List<List<String>> suffixWays = allConstruct(suffix, wordBank, memo);
                List<List<String>> targetWays = new ArrayList<>();
                for (List<String> way : suffixWays) {
                    List<String> subList = new ArrayList<>(way);
                    subList.add(0, word);
                    targetWays.add(subList);
                }
                totalWays.addAll(targetWays);
            }
        }

        memo.put(target, totalWays);
        return totalWays;
    }

    public static void main(String[] args) {
        String target = "purple";
        String[] wordBank = {"purp", "p", "ur", "le", "purpl"};
        long startTime = System.nanoTime();

        Map<String, List<List<String>>> memo = new HashMap<>();
        List<List<String>> ways = allConstruct(target, wordBank, memo);

        long endTime = System.nanoTime();
        double duration = (endTime - startTime) / 1e9;

        System.out.println("Number of ways: " + ways.size());
        for (List<String> way : ways) {
            System.out.println(way);
        }
        System.out.printf("Runtime: %.6f seconds\n", duration);
    }
}
