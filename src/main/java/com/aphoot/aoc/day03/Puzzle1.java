package com.aphoot.aoc.day03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Puzzle1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long productSum = 0;
        String line;
        while ((line = br.readLine()) != null) {
            Pattern pattern = Pattern.compile("mul\\((\\d{1,3}),(\\d{1,3})\\)");
            Matcher matcher = pattern.matcher(line);
            var end = 0;
            var start = 0;

            while(matcher.find(end)) {
                System.out.println("-----------");
                start = matcher.start();
                end = matcher.end();
                System.out.println(line.substring(start, end));
                var product = Integer.parseInt(matcher.group(1)) * Integer.parseInt(matcher.group(2));
                System.out.println(product);
                productSum += product;

            }
            System.out.println(productSum);
        }
    }
}
