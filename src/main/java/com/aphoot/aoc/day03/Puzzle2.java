package com.aphoot.aoc.day03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class Puzzle2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long productSum = 0;
        String line;
        boolean doMult = true; //start state is to DO the multiplication
        while ((line = br.readLine()) != null) {
            Pattern multPattern = Pattern.compile("mul\\((\\d{1,3}),(\\d{1,3})\\)");
            Pattern dontPattern = Pattern.compile("don't\\(\\)");
            Pattern doPattern = Pattern.compile("do\\(\\)");
            int index = 0;
            var multMatcher = multPattern.matcher(line);
            var doMatcher = doPattern.matcher(line);
            var dontMatcher = dontPattern.matcher(line);

            while(multMatcher.find(index) || doMatcher.find(index) || dontMatcher.find(index)) {
                var startMult = multMatcher.find(index) ? multMatcher.start() : Integer.MAX_VALUE;
                var startDo = doMatcher.find(index) ? doMatcher.start() : Integer.MAX_VALUE;
                var startDont = dontMatcher.find(index) ? dontMatcher.start() : Integer.MAX_VALUE;

                if (startMult == Math.min(Math.min(startDont, startDo), startMult)) {
                    index = multMatcher.end();
                    if (!doMult)
                        continue;
                    var product = Integer.parseInt(multMatcher.group(1)) * Integer.parseInt(multMatcher.group(2));
                    System.out.println(product);
                    productSum += product;
                } else if (startDo == Math.min(startDo, startDont)) {
                    //do()
                    doMult = true;
                    index = doMatcher.end();
                } else {
                    //don't()
                    doMult = false;
                    index = dontMatcher.end();
                }
            }
            System.out.println(productSum);

        }
    }
}
