package year2022.day4;

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
            int sum = 0;
            int sum2 = 0;
            String line;
            while ((line = br.readLine()) != null) {
                int[] arr = Arrays.stream(line.split(",|-")).mapToInt(Integer::parseInt).toArray();
                sum += part1(arr);
                sum2 += part2(arr);
            }
            System.out.println("part 1: " + sum);
            System.out.println("part 2: " + sum2);
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }

    public int part1(int[] arr) {
        int sum = 0;

        boolean rightInsideLeft = arr[2] >= arr[0] && arr[1] >= arr[3];
        boolean leftInsideRight = arr[0] >= arr[2] && arr[1] <= arr[3];

        if (rightInsideLeft || leftInsideRight) {
            sum++;
        }
        return sum;
    }

    public int part2(int[] arr) {
        int sum = 0;

        boolean rightInsideLeft = arr[3] <= arr[1] && arr[3] >= arr[0];
        boolean leftInsideRight = arr[1] <= arr[3] && arr[1] >= arr[2];
        if (leftInsideRight) {
            sum++;
        } else if (rightInsideLeft) {
            sum++;
        }
        return sum;
    }

    public String getPath() {
        String[] arr = "year2022.day4".split("\\.");
        String year = arr[0].replace("year", "");
        String day = arr[1];
        return String.format("files/%s/%s.txt", year, day);
    }
}