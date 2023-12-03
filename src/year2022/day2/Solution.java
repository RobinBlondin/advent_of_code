package year2022.day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Solution {

    public static void main(String[] args) {
        new Solution().solution();
    }

    public void solution() {
        try (BufferedReader br = new BufferedReader(new FileReader(getPath()))) {
            String line;
            int sum1 = 0;
            int sum2 = 0;
            while ((line = br.readLine()) != null) {
                String[] arr = line.split(" ");

                //part 1
                if(Values.getEnum(arr[0]).getBeats().equals(arr[1])){
                   sum1 += Values.getEnum(arr[1]).getPoints();
                } else if (Values.getEnum(arr[0]).getBeatenBy().equals(arr[1])){
                    sum1 += Values.getEnum(arr[1]).getPoints() + 6;
                } else {
                    sum1 += Values.getEnum(arr[1]).getPoints() + 3;
                }

                //part 2
                switch (arr[1]) {
                    case "X" -> sum2 += Values.getEnum(Values.getEnum(arr[0]).getBeats()).getPoints();
                    case "Y" -> sum2 += Values.getEnum(arr[0]).getPoints() + 3;
                    case "Z" -> sum2 += Values.getEnum(Values.getEnum(arr[0]).getBeatenBy()).getPoints() + 6;
                }
            }
            System.out.println("Part 1: " + sum1);
            System.out.println("Part 2: " + sum2);
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }

    public String getPath() {
        String[] arr = "year2022.day2".split("\\.");
        String year = arr[0].replace("year", "");
        String day = arr[1];
        return String.format("files/%s/%s.txt", year, day);
    }
}