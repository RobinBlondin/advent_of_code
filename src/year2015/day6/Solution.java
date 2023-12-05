package year2015.day6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    private final int[][] grid1;
    private final int[][] grid2;
    private final List<String> operations;

    public Solution() {
        grid1 = new int[1000][1000];
        grid2 = new int[1000][1000];
        operations = new ArrayList<>();
        readFile();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.solution();
    }

    public void readFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(getPath()))) {
            String line;
            while ((line = br.readLine()) != null) {
                operations.add(line);
            }
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }

    public void solution() {
        for (String operation : operations) {
            String[] arr = operation.split(" ");
            String[] start = arr[arr.length - 3].split(",");
            String[] end = arr[arr.length - 1].split(",");
            int startX = Integer.parseInt(start[0]);
            int startY = Integer.parseInt(start[1]);
            int endX = Integer.parseInt(end[0]);
            int endY = Integer.parseInt(end[1]);

            for (int i = startX; i <= endX; i++) {
                for (int j = startY; j <= endY; j++) {
                    part1(i, j, arr[0], arr[1]);
                    part2(i, j, arr[0], arr[1]);
                }
            }
        }
        countLights();
        countBrightness();
    }

    public void part1(int i, int j, String operation1, String operation2) {
        if (operation1.equals("toggle")) {
            grid1[i][j] = grid1[i][j] == 0 ? 1 : 0;
        } else if (operation2.equals("on")) {
            grid1[i][j] = 1;
        } else {
            grid1[i][j] = 0;
        }
    }

    public void part2(int i, int j, String operation1, String operation2) {
        if (operation1.equals("toggle")) {
            grid2[i][j] += 2;
        } else if (operation2.equals("on")) {
            grid2[i][j] += 1;
        } else {
            grid2[i][j] = grid2[i][j] == 0 ? 0 : grid2[i][j] - 1;
        }
    }

    public void countLights() {
        int sum = 0;
        for (int[] row : grid1) {
            for (int element : row) {
                if (element == 1) {
                    sum++;
                }
            }
        }
        System.out.println("Part 1: " + sum);
    }

    public void countBrightness() {
        int sum = 0;
        for (int[] row : grid2) {
            for (int element : row) {
                sum += element;
            }
        }
        System.out.println("Part 2: " + sum);
    }

    public String getPath() {
        String[] arr = "year2015.day6".split("\\.");
        String year = arr[0].replace("year", "");
        String day = arr[1];
        return String.format("files/%s/%s.txt", year, day);
    }
}