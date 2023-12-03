package year2015.day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        new Solution().solution();
    }

    public void solution() {
        try (BufferedReader br = new BufferedReader(new FileReader(getPath()))) {
            char[] input = br.readLine().toCharArray();
            br.close();
            //--- End of parsing file ---//

            int length = input.length / 2;
            int[][] grid = new int[length][length];

            int yAxisSanta = length / 2,
                    yAxisRobot = length / 2,
                    xAxisSanta = length / 2,
                    xAxisRobot = length / 2;

            for (int i = 0; i < input.length; i++) {
                boolean isEven = i % 2 == 0;

                int currentYAxis = isEven ? yAxisSanta : yAxisRobot;
                int currentXAxis = isEven ? xAxisSanta : xAxisRobot;

                switch (input[i]) {
                    case '^':
                        currentYAxis--;
                        break;
                    case 'v':
                        currentYAxis++;
                        break;
                    case '>':
                        currentXAxis++;
                        break;
                    case '<':
                        currentXAxis--;
                        break;
                }

                if (isEven) {
                    yAxisSanta = currentYAxis;
                    xAxisSanta = currentXAxis;
                    grid[yAxisSanta][xAxisSanta] = 1;
                } else {
                    yAxisRobot = currentYAxis;
                    xAxisRobot = currentXAxis;
                    grid[yAxisRobot][xAxisRobot] = 1;
                }
            }

            long count = Arrays.stream(grid)
                    .flatMapToInt(Arrays::stream)
                    .filter(val -> val != 0)
                    .count();
            System.out.println(count);
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }

    public String getPath() {
        String[] arr = "year2015.day3".split("\\.");
        String year = arr[0].replace("year", "");
        String day = arr[1];
        return String.format("files/%s/%s.txt", year, day);
    }
}