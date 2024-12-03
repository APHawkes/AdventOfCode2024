package com.aphoot.aoc.day01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Puzzle2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        List<Integer> leftList = new ArrayList<>();
        Map<Integer, Integer> rightIntCount = new TreeMap<>();
        while ((line = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(line, " ", false);
            leftList.add(Integer.parseInt(st.nextToken()));
            rightIntCount.merge(Integer.parseInt(st.nextToken()), 1, (a,b)->a+1);
        }

        int sumOfScores = 0;
        for (int i = 0; i < leftList.size(); i++) {
            var score = Math.abs(leftList.get(i) * rightIntCount.getOrDefault(leftList.get(i),0));
            sumOfScores += score;
        }
        System.out.println(sumOfScores);
    }
}
