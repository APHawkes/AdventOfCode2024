package com.aphoot.aoc.day06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Puzzle1 {
    static final int[] xVelocities = {0, 1, 0, -1};
    static final int[] yVelocities = {-1, 0, 1, 0};
    private static int guardDirection;
    private static int guardY;
    private static int guardX;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        List<char[]> charArrayList = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            charArrayList.add(line.toCharArray());
        }

        //fill char grid[y][x];
        var grid = new char[charArrayList.getFirst().length][charArrayList.size()];
        for (int y = 0; y < charArrayList.size(); y++) {
            grid[y] = charArrayList.get(y);
        }

        // init guard
        guardX = 0;
        guardY = 0;
        //starts going UP
        guardDirection = 0;

        FindGuard:
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                if (grid[y][x] == '^') {
                    guardX = x;
                    guardY = y;
                    break FindGuard;
                }
            }
        }

        //guard moves
        while (!guardAtEdge(guardX, guardY, grid[0].length - 1, grid.length - 1)) {
            //identify new direction?
            if (grid[guardY + yVelocities[guardDirection]][guardX + xVelocities[guardDirection]] == '#') {
                turnRight();
            }
            //mark current position
            grid[guardY][guardX] = 'X';
            //move guard
            guardX += xVelocities[guardDirection];
            guardY += yVelocities[guardDirection];
            //mark current position
            grid[guardY][guardX] = 'X';//again, in case first/last

        }

        int stepCount = 0;
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                if (grid[y][x] == 'X') {
                    stepCount++;
                }
            }
        }
        System.out.println(stepCount);
    }

    private static void turnRight() {
        guardDirection++;
        if (guardDirection == 4)
            guardDirection = 0;
    }

    private static boolean guardAtEdge(int guardX, int guardY, int maxX, int maxY) {
        return (guardX == 0 || guardX == maxX || guardY == 0 || guardY == maxY);
    }
}
