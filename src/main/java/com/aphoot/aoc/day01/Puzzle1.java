package com.aphoot.aoc.day01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Puzzle1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        List<Integer> leftList = new ArrayList<>();
        List<Integer> rightList = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(line, " ", false);
            leftList.add(Integer.parseInt(st.nextToken()));
            rightList.add(Integer.parseInt(st.nextToken()));
        }
        leftList.sort(Integer::compareTo);
        rightList.sort(Integer::compareTo);

        int sumOfDistances = 0;
        for (int i = 0; i < leftList.size(); i++) {
            sumOfDistances += Math.abs(leftList.get(i) - rightList.get(i));
        }
        System.out.println(sumOfDistances);
    }
}
