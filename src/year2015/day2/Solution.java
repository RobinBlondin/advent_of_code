package year2015.day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        new Solution().solution();
    }

    public void solution() {
        try (BufferedReader br = new BufferedReader(new FileReader(getPath()))) {
            List<String[]> input = new ArrayList<>();

            String line;
            while ((line = br.readLine()) != null) {
                input.add(line.split("x"));
            }

            br.close();
            // --- Parsing of file done --- //
            long wrappingPaper = 0;
            long ribbon = 0;
            for (String[] strings : input) {
                int length = Integer.parseInt(strings[0]);
                int width = Integer.parseInt(strings[1]);
                int height = Integer.parseInt(strings[2]);
                List<Integer> dimensions = (Arrays.asList(length, width, height));
                Collections.sort(dimensions);

                int areaOne = length * width;
                int areaTwo = width * height;
                int areaThree = length * height;
                int smallestArea = Collections.min(new ArrayList<>(Arrays.asList(areaOne,areaTwo,areaThree)));
                wrappingPaper += (2L *areaOne) + (2L *areaTwo) + (2L *areaThree) + smallestArea;
                ribbon += (2L *dimensions.get(0)) + (2L *dimensions.get(1)) + ((long) length *width*height);
            }
            System.out.println("Part 1: " + wrappingPaper + "\n\n" + "Part 2: " + ribbon);
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }

    public String getPath() {
        String[] arr = "year2015.day2".split("\\.");
        String year = arr[0].replace("year", "");
        String day = arr[1];
        return String.format("files/%s/%s.txt", year, day);
    }
}