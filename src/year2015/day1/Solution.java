package year2015.day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) {
        new Solution().solution();
    }

    public void solution() {
        try (BufferedReader br = new BufferedReader(new FileReader(getPath()))) {
            char[] input = br.readLine().toCharArray();
            br.close();

            int count = 0;
            int cellarPosition = Integer.MAX_VALUE;
            for (int i = 0; i < input.length; i++) {
                if (count < 0 && i < cellarPosition) {
                    cellarPosition = i;
                }

                if (input[i] == '(') {
                    count++;
                } else if (input[i] == ')') {
                    count--;
                }
            }
            System.out.println("Part 1: " + count + "\n\n" + "Part 1: " + cellarPosition);
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }

    public String getPath() {
        String[] arr = "year2015.day1".split("\\.");
        String year = arr[0].replace("year", "");
        String day = arr[1];
        return String.format("files/%s/%s.txt", year, day);
    }
}