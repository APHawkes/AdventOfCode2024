package com.aphoot.aoc.day08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Puzzle1 {
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

        //Going top>bottom, left>right, find a non-period (frequency)
        Pos antennaPos = new Pos(-1, 0); //start just before 0,0
        Set<Pos> antinodes = new HashSet<>();
        while ((antennaPos = findNextAntenna(grid, antennaPos)) != null) {
            Pos nextMatchingAntenna = antennaPos;
            while ((nextMatchingAntenna = findNextAntenna(grid, nextMatchingAntenna, grid[antennaPos.y][antennaPos.x])) != null) {
                addAntinodes(antennaPos,nextMatchingAntenna, grid, antinodes);
            }
        }

        System.out.println(antinodes);
        System.out.println(antinodes.size());
    }

    private static void addAntinodes(Pos antennaPos, Pos nextMatchingAntenna, char[][] grid, Set<Pos> antinodes) {
        int deltaX = nextMatchingAntenna.x - antennaPos.x;
        int deltaY = nextMatchingAntenna.y - antennaPos.y;

        Pos antinodeNear = new Pos(antennaPos.x - deltaX, antennaPos.y - deltaY);
        Pos antinodeFar = new Pos(nextMatchingAntenna.x + deltaX, nextMatchingAntenna.y + deltaY);

        if (antinodeExists(antinodeNear, grid)) {
            antinodes.add(antinodeNear);
        }
        if (antinodeExists(antinodeFar, grid)) {
            antinodes.add(antinodeFar);
        }
    }

    private static boolean antinodeExists(Pos pos, char[][] grid) {
        if (pos.x >= 0 && pos.x < grid[0].length &&
               pos.y >= 0 && pos.y < grid.length) {
            System.out.println(pos.x + "," + pos.y);
            return true;
        }
        return false;
    }

    private static Pos findNextAntenna(char[][] grid, Pos startPos) {
        for (int y = startPos.y; y < grid.length; y++) {
            for (int x = (y == startPos.y) ? startPos.x+1 : 0; x < grid[y].length; x++) {
                if (grid[y][x] != '.') {
                    return new Pos(x,y);
                }
            }
        }
        return null; //not found
    }

    private static Pos findNextAntenna(char[][] grid, Pos startPos, char frequency) {
        for (int y = startPos.y; y < grid.length; y++) {
            for (int x = (y == startPos.y) ? startPos.x+1 : 0; x < grid[0].length; x++) {
                if (grid[y][x] == frequency) {
                    return new Pos(x,y);
                }
            }
        }
        return null; //not found
    }

    private static class Pos {
        public int x;
        public int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Pos pos)) return false;
            return x == pos.x && y == pos.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "Pos{" +
                   "x=" + x +
                   ", y=" + y +
                   '}';
        }
    }
}
