package com.aphoot.aoc.day02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Puzzle1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String report;
        int safeCount = 0;
        while ((report = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(report, " ");
            List<Integer> levels = new ArrayList<>();
            while (st.hasMoreTokens()) {
                 levels.add(Integer.parseInt(st.nextToken()));
            }
            if (isSafe(levels)){
                safeCount++;
            }

        }
        System.out.println(safeCount);
    }

    static boolean isSafe(List<Integer> intList){
        boolean isIncreasing = false;
        int prev = intList.getFirst();
        for (int i = 1; i < intList.size(); i++) {
            if (i == 1) {
                isIncreasing = intList.get(i) > prev;
            }

            if (intList.get(i) > prev != isIncreasing) {
                return false;
            }
            if (Math.abs(intList.get(i) - prev) > 3 || Math.abs(intList.get(i) - prev) == 0) {
                return false;
            }
            prev = intList.get(i);
        }
        return true;
    }
}
