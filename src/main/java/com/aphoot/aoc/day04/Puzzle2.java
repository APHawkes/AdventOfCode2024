package com.aphoot.aoc.day04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Puzzle2 {
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
                xmasCount += checkDir(x,y,'M','S','M','S');
                xmasCount += checkDir(x,y,'S','M','S','M');
                xmasCount += checkDir(x,y,'M','M','S','S');
                xmasCount += checkDir(x,y,'S','S','M','M');
            }
        }
        System.out.println(xmasCount);
    }

    private static int checkDir(int x, int y, char tl, char tr, char bl, char br) {
        if (x+2 >= xSize || x < 0 || y+2 >= ySize || y < 0)
            return 0; // word extends beyond edge
        if (   grid[y][x] == tl
               && grid[y+1][x+1] == 'A'
               && grid[y][x+2] == tr
               && grid[y+2][x] == bl
               && grid[y+2][x+2] == br
        )
            return 1;
        return 0;
    }
}
