package year2015.day5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    private List<String> lines;
    private final String VOWELS = "aeiou";
    private final String[] BAD_STRINGS = {"ab", "cd", "pq", "xy"};

    public Solution() {
        this.lines = new ArrayList<>();
    }
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.readFile();
        solution.part1();
        solution.part2();
    }

    public void readFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(getPath()))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }

    public void part1() {
        int sum = 0;

        for (String line : lines) {
            int vowels = 0;
            boolean containDoubles = false;
            boolean containBadStrings = false;
            for (int i = 0; i < line.length(); i++) {
                if (VOWELS.contains(String.valueOf(line.charAt(i)))) {
                    vowels++;
                }
                if (i > 0 && line.charAt(i) == line.charAt(i - 1)) {
                    containDoubles = true;
                }
            }

            for (String badString : BAD_STRINGS) {
                if (line.contains(badString)) {
                    containBadStrings = true;
                    break;
                }
            }
            if (vowels >= 3 && containDoubles && !containBadStrings) {
                sum++;
            }
        }
        System.out.println("Part 1: " + sum);
    }

    public void part2() {
        int sum = 0;
        for (String line : lines) {
            boolean containPair = false;
            boolean containRepeat = false;
            for (int i = 0; i < line.length() - 1; i++) {
                String pair = line.substring(i, i + 2);
                if (line.indexOf(pair, i + 2) != -1) {
                    containPair = true;
                }
                if (i < line.length() - 2 && line.charAt(i) == line.charAt(i + 2)) {
                    containRepeat = true;
                }
            }
            if (containPair && containRepeat) {
                sum++;
            }
        }
        System.out.println("Part 2: " + sum);
    }

    public String getPath() {
        String[] arr = "year2015.day5.txt".split("\\.");
        String year = arr[0].replace("year", "");
        String day = arr[1];
        return String.format("files/%s/%s.txt", year, day);
    }
}