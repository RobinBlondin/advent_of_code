package year2022.day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    private final int UPPER_CASE_COMPENSATION = 38;
    private final int LOWER_CASE_COMPENSATION = 96;
    private final List<String> list;
    public Solution() {
        list = new ArrayList<>();
        readFileToList();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println("Part 1: " + solution.part1());
        System.out.println("Part 2: " + solution.part2());
    }

    public void readFileToList() {
        try (BufferedReader br = new BufferedReader(new FileReader(getPath()))) {
            String line;
            while ((line = br.readLine()) != null) {
                list.add(line);
            }
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }

    public int part1() {
        int sum = 0;
        for (String str : list) {
            String first = str.substring(0, str.length() / 2);
            String second = str.substring(str.length() / 2);
            for (char c : first.toCharArray()) {
                if (second.contains(String.valueOf(c))) {
                    if (Character.isUpperCase(c)) {
                        sum += c - UPPER_CASE_COMPENSATION;
                    } else {
                        sum += c - LOWER_CASE_COMPENSATION;
                    }
                    break;
                }
            }
        }
        return sum;
    }

    public int part2() {
        int sum = 0;
        for (int i = 0; i < list.size(); i += 3) {
            List<String> sublist = list.subList(i, i+3);
            for(char c : sublist.get(0).toCharArray()) {
                if (sublist.get(1).contains(String.valueOf(c)) && sublist.get(2).contains(String.valueOf(c))) {
                    if (Character.isUpperCase(c)) {
                        sum += c - UPPER_CASE_COMPENSATION;
                    } else {
                        sum += c - LOWER_CASE_COMPENSATION;
                    }
                    break;
                }
            }
        }
        return sum;
    }

    public String getPath() {
        String[] arr = "year2022.day3.txt".split("\\.");
        String year = arr[0].replace("year", "");
        String day = arr[1];
        return String.format("files/%s/%s.txt", year, day);
    }
}