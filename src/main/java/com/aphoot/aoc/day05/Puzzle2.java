package com.aphoot.aoc.day05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Puzzle2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        boolean collectingRules = true;
        Map<Integer, Set<Integer>> rules = new TreeMap<>();
        int middlePageSum = 0;
        while ((line = br.readLine()) != null) {
            if (line.isEmpty()){
                collectingRules = false;
                continue;
            }

            StringTokenizer tokenizer = new StringTokenizer(line, "|,");

            //Collect Rules
            if (collectingRules) {
                var leadingPage = Integer.valueOf(tokenizer.nextToken());
                var followingPage = Integer.valueOf(tokenizer.nextToken());
                rules.computeIfAbsent(leadingPage, k -> new TreeSet<>());
                rules.get(leadingPage).add(followingPage);
            }

            // Checking updates
            if (!collectingRules) {
                //read update
                List<Integer> update = new ArrayList<>();
                while (tokenizer.hasMoreTokens()) {
                    update.add(Integer.valueOf(tokenizer.nextToken()));
                }

                if (!followsRules(update, rules)) {
                    //push pages left in the list until all rules for that page are followed
                    for (int i = 0; i < update.size(); i++) {
                        var currentPage = update.get(i);
                        var currentPageRules = rules.getOrDefault(currentPage, Set.of());
                        var leftPages = new ArrayList<>(update.subList(0, i));
                        for (int j = 0; j < leftPages.size(); j++) {
                            //find first rule violation, move current page to the left of first violation
                            if (currentPageRules.contains(leftPages.get(j))) {
                                update.remove(i);
                                update.add(j, currentPage);
                                break;//if we already moved to first rule violation, no need to check further
                            }
                        }
                    }

                    //find middle page
                    int middleIndex = update.size()/2;
                    middlePageSum += update.get(middleIndex);
                }
            }
        }
        System.out.println(rules);
        System.out.println(middlePageSum);
    }

    private static boolean followsRules(List<Integer> update, Map<Integer, Set<Integer>> rules) {
        boolean ruleBroken = false;
        CheckRule:
        for (int currentPageIndex = 0; currentPageIndex < update.size(); currentPageIndex++) {
            var currentPage = update.get(currentPageIndex);
            var pageRules = rules.get(currentPage);
            for (int checked = 0; checked < currentPageIndex; checked++) {
                if (pageRules != null && pageRules.contains(update.get(checked))) {
                    ruleBroken = true;
                    break CheckRule;
                }
            }
        }
        return !ruleBroken;
    }
}
