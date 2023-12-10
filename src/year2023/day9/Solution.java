package year2023.day9;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    List<List<Integer>> result;
    List<List<Integer>> temp;
    public Solution() {
        result = new ArrayList<>();
        temp = new ArrayList<>();
        readFile();
    }
    public static void main(String[] args) {
       Solution solution = new Solution();
        solution.solution();
    }

    public void solution() {
        long sum = 0;
        long sum2 = 0;
        for (List<Integer> integers : temp) {
            result.add(integers);
            List<Integer> list = new ArrayList<>();
            int index = 1;
            while (true) {
                if (index >= result.getLast().size()) {
                    result.add(new ArrayList<>(list));
                    list.clear();
                    index = 1;
                    if (result.getLast().stream().allMatch(e -> e == 0)) {
                        break;
                    } else {
                        continue;
                    }
                }
                int number = result.getLast().get(index) - result.getLast().get(index - 1);
                list.add(number);
                index++;
            }
            sum += part1();
            sum2 += part2();
            result.clear();
        }
        System.out.println("Part 1: " + sum);
        System.out.println("Part 2: " + sum2);
    }

    public long part1() {
        long sum = 0;
        for (int i = result.size() - 1; i >= 0 ; i--) {
            sum += result.get(i).getLast();
        }
        return sum;

    }

    public long part2() {
        long sum = 0;
        for (int i = result.size() - 1; i >= 0; i--) {
            sum = result.get(i).getFirst() - sum;
        }
        return sum;
    }

    public void readFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(getPath()))) {
            String line;
            while ((line = br.readLine()) != null) {
                temp.add(Arrays.stream((line.split(" "))).
                        map(Integer::parseInt).toList());
            }
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }

    public String getPath() {
        String[] arr = "year2023.day9".split("\\.");
        String year = arr[0].replace("year", "");
        String day = arr[1];
        return String.format("files/%s/%s.txt", year, day);
    }
}