package year2022.day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
    List<Integer> calories;

    public Solution() {
        this.calories = new ArrayList<>();
    }

    public static void main(String[] args) {
        new Solution().solution();
    }

    public void solution() {
        try (BufferedReader br = new BufferedReader(new FileReader(getPath()))) {
            String line;

            int temp = 0;
            while ((line = br.readLine()) != null) {
                if (line.isEmpty()) {
                    calories.add(temp);
                    temp = 0;
                    continue;
                }
                temp += Integer.parseInt(line);

                Collections.sort(calories);
                Collections.reverse(calories);
            }
            System.out.println("Part 1: " + calories.get(0));
            System.out.println("Part 2: " + (calories.get(0) + calories.get(1) + calories.get(2)));
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }

    public String getPath() {
        String[] arr = "year2022.day1".split("\\.");
        String year = arr[0].replace("year", "");
        String day = arr[1];
        return String.format("files/%s/%s.txt", year, day);
    }
}