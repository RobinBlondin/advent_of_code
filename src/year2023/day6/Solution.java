package year2023.day6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Solution {
    private int[] times;
    private int[] records;

    public Solution() {
        readFile();
    }
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.part1();
        solution.part2();
    }

    public void part1() {
        List<Long> counter = new ArrayList<>();
        int sum = 1;
        for (int j = 0; j < times.length; j++) {
            counter.add(countWinningRaces(times[j], records[j]));
        }
        for (Long num : counter) {
            sum *= num;
        }
        System.out.println("Part 1: " + sum);

    }

    public void part2() {
        StringBuilder timeString = new StringBuilder();
        StringBuilder recordString = new StringBuilder();
        for (int i = 0; i < times.length; i++) {
            timeString.append(times[i]);
            recordString.append(records[i]);
        }
        long time = Long.parseLong(timeString.toString());
        long record = Long.parseLong(recordString.toString());

        long count = countWinningRaces(time, record);
        System.out.println("Part 2: " + count);
    }

    public long countWinningRaces(long time, long record) {
        long count = 0;
        long increment = 0;
        for (long i = time; i > 0; i--) {
            if (i * increment > record) {
                count++;
            }
            increment++;
        }
        return count;
    }

    public void readFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(getPath()))) {
            String line = br.readLine();
            times = Arrays.stream(line.split(": +")[1].split(" +")).mapToInt(Integer::parseInt).toArray();

            line = br.readLine();
            records = Arrays.stream(line.split(": +")[1].split(" +")).mapToInt(Integer::parseInt).toArray();


        } catch (IOException e) {
            System.out.println("File not found");
        }
    }

    public String getPath() {
        String[] arr = "year2023.day6".split("\\.");
        String year = arr[0].replace("year", "");
        String day = arr[1];
        return String.format("files/%s/%s.txt", year, day);
    }
}