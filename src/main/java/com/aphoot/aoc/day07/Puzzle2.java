package com.aphoot.aoc.day07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Puzzle2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        List<ArrayList<Long>> equations = new ArrayList<>();

        while ((line = br.readLine()) != null) {
            StringTokenizer tokenizer = new StringTokenizer(line, ": ");
            ArrayList<Long> list = new ArrayList<>();
            while (tokenizer.hasMoreTokens()) {
                list.add(Long.parseLong(tokenizer.nextToken()));
            }
            equations.add(list);
        }
        long solutionsFound = 0L;
        for(List<Long> equation : equations) {
            long expectedAnswer = equation.getFirst();
            if (evaluateExpression(equation.get(1), equation.subList(2, equation.size()), expectedAnswer)) {
                solutionsFound += expectedAnswer;
            }
        }

        System.out.println(solutionsFound);
    }

    private static boolean evaluateExpression(long accumulator, List<Long> expressions, long expectedAnswer) {
        if (expressions.isEmpty()){
            return accumulator == expectedAnswer;
        }
        Long car = expressions.getFirst();
        List<Long> cdr =  expressions.subList(1, expressions.size());

        return evaluateExpression(accumulator+car, cdr, expectedAnswer)
                || evaluateExpression(accumulator*car, cdr, expectedAnswer)
                || evaluateExpression(Long.parseLong(Long.toString(accumulator) + Long.toString(car)), cdr, expectedAnswer);
    }
}
