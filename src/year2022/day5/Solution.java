package year2022.day5;

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
            while ((line = br.readLine()) != null) {

            }
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }

    public String getPath() {
        String[] arr = "year2022.day5".split("\\.");
        String year = arr[0].replace("year", "");
        String day = arr[1];
        return String.format("files/%s/%s.txt", year, day);
    }
}