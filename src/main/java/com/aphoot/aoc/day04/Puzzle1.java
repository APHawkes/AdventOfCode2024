package com.aphoot.aoc.day04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Puzzle1 {
    static int xSize;
    static int ySize;
    static char[][] grid;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        List<char[]> arrayList = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            var lineChars = line.toCharArray();
            arrayList.add(lineChars);
        }
        xSize = arrayList.getFirst().length;
        ySize = arrayList.size();
        grid = new char[ySize][xSize];

        for (int i = 0; i < ySize; i++) {
            grid[i] = arrayList.get(i);
        }

        int xmasCount = 0;
        for (int y = 0; y < ySize; y++) {
            for (int x = 0; x < xSize; x++) {
                xmasCount += checkDir(x,y,-1,-1);
                xmasCount += checkDir(x,y,-1,0);
                xmasCount += checkDir(x,y,-1,1);
                xmasCount += checkDir(x,y,0,-1);
                xmasCount += checkDir(x,y,0,1);
                xmasCount += checkDir(x,y,1,-1);
                xmasCount += checkDir(x,y,1,0);
                xmasCount += checkDir(x,y,1,1);
            }
        }
        System.out.println(xmasCount);
    }

    private static int checkDir(int x, int y, int xInc, int yInc) {
        if (x+3*xInc >= xSize || x+3*xInc < 0 || y+3*yInc >= ySize || y+3*yInc < 0)
            return 0; // word extends beyond edge
        if (   grid[y][x] == 'X'
            && grid[y+yInc][x+xInc] == 'M'
            && grid[y+2*yInc][x+2*xInc] == 'A'
            && grid[y+3*yInc][x+3*xInc] == 'S')
            return 1;
        return 0;
    }
}
