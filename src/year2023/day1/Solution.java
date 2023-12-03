package year2023.day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    Map<String, String> map;
    public Solution() {
        this.map = new HashMap<>();
        map.put("one", "1");
        map.put("two", "2");
        map.put("three", "3");
        map.put("four", "4");
        map.put("five", "5");
        map.put("six", "6");
        map.put("seven", "7");
        map.put("eight", "8");
        map.put("nine", "9");
    }

    public static void main(String[] args) {
        new Solution().solution();
    }

    public void solution() {
        try (BufferedReader br = new BufferedReader(new FileReader(getPath()))) {

            int sum = 0;
            String line;
            while ((line = br.readLine()) != null) {
                line = " " + line + " ";
                sum += Integer.parseInt(
                         new StringBuilder()
                        .append(getFirstNumber(line))
                        .append(getLastNumber(line))
                        .toString());
            }
            System.out.println(sum);
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }

    public String getPath() {
        String[] arr = "year2023.day1".split("\\.");
        String year = arr[0].replace("year", "");
        String day = arr[1];
        return String.format("files/%s/%s.txt", year, day);
    }

    public String getFirstNumber(String line) {
        boolean found = false;
        int start = 0;
        StringBuilder result = new StringBuilder();

        for (int i = 1; i < line.length(); i++) {
            for (String number : map.keySet()) {
                if(line.substring(start, i).contains(map.get(number))) {
                    result.append(map.get(number));
                    found = true;
                    break;
                } else if (line.substring(start, i).contains(number)) {
                    result.append(map.get(number));
                    found = true;
                    break;
                }
            }
            if(found)
                break;
        }
        return result.toString();
    }

    public String getLastNumber(String line) {
        boolean found = false;
        int start = line.length()-1;
        StringBuilder result = new StringBuilder();

        for (int i = start; i >= 0; i--) {
            for (String number : map.keySet()) {
                if(line.substring(i, start).contains(map.get(number))) {
                    result.append(map.get(number));
                    found = true;
                    break;
                } else if (line.substring(i, start).contains(number)) {
                    result.append(map.get(number));
                    found = true;
                    break;
                }
            }
            if(found)
                break;
        }
        return result.toString();
    }
}