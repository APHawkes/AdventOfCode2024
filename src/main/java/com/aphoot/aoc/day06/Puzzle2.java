package com.aphoot.aoc.day06;

import javax.naming.Context;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Puzzle2 {
    static final int[] xVelocities = {0, 1, 0, -1};
    static final int[] yVelocities = {-1, 0, 1, 0};
    private static int obstaclePossibilities;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        List<char[]> charArrayList = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            charArrayList.add(line.toCharArray());
        }

        //fill char grid[y][x];
        var grid = buildGrid(charArrayList);
        obstaclePossibilities = 0;

        //guard moves
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                var tempCtx = new Puzzle2.GridContext();
                tempCtx.grid = copyGrid(grid);
                tempCtx.grid[y][x] = '#';
                obstaclePossibilities += runGuard(tempCtx);
            }
        }
        //654 too low
        //1630 too high
        //1605 not right

        System.out.println(obstaclePossibilities);
    }

    private static char[][] copyGrid(char[][] grid) {
        var copyGrid = new char[grid.length][grid[0].length];
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                copyGrid[y][x] = grid[y][x];
            }
        }
        return copyGrid;
    }

    private static char[][] buildGrid(List<char[]> charArrayList) {
        var grid = new char[charArrayList.getFirst().length][charArrayList.size()];
        for (int y = 0; y < charArrayList.size(); y++) {
            grid[y] = charArrayList.get(y);
        }
        return grid;
    }

    private static int runGuard(GridContext ctx) {
        int stepCounter = 0;
        findGuard(ctx);
        if (ctx.guardX <0) {
            return 0; //overwrote guard start position
        }
        //starts going UP
        ctx.guardDirection = 0;
        //mark starting position
        ctx.grid[ctx.guardY][ctx.guardX] = Character.forDigit(ctx.guardDirection, 10);

        while (!guardAtEdge(ctx.guardX, ctx.guardY, ctx.grid[0].length - 1, ctx.grid.length - 1)) {
            //identify new direction?
            while (ctx.grid[ctx.guardY + yVelocities[ctx.guardDirection]][ctx.guardX + xVelocities[ctx.guardDirection]] == '#') {
                ctx.guardDirection = turnRight(ctx.guardDirection);
            }

            //move guard
            ctx.guardX += xVelocities[ctx.guardDirection];
            ctx.guardY += yVelocities[ctx.guardDirection];

            //Am I retracing my step?
            if (ctx.grid[ctx.guardY][ctx.guardX] == Character.forDigit(ctx.guardDirection, 10)){
                return 1;
            }
            //mark new position
            ctx.grid[ctx.guardY][ctx.guardX] = Character.forDigit(ctx.guardDirection, 10);//again, in case first/last
            stepCounter++;
            if (stepCounter > 1000000) { //I must be in a loop if going on this long
                return 1;
            }
        }
        return 0;
    }

    private static class GridContext {
        public int guardX = 0;
        public int guardY = 0;
        public int guardDirection = 0;
        public char[][] grid;
    }

    private static void findGuard(Puzzle2.GridContext ctx) {
        ctx.guardX = -1;
        ctx.guardY = -1;
        for (int y = 0; y < ctx.grid.length; y++) {
            for (int x = 0; x < ctx.grid[y].length; x++) {
                if (ctx.grid[y][x] == '^') {
                    ctx.guardX = x;
                    ctx.guardY = y;
                    return;
                }
            }
        }
    }

    private static int turnRight(int guardDirection) {
        guardDirection++;
        if (guardDirection == 4)
            guardDirection = 0;
        return guardDirection;
    }

    private static boolean guardAtEdge(int guardX, int guardY, int maxX, int maxY) {
        return (guardX == 0 || guardX == maxX || guardY == 0 || guardY == maxY);
    }
}
