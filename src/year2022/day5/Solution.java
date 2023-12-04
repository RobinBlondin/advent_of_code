package year2022.day5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class Solution {
    char[][] stacks;
    List<List<String>> parsedStacks;
    List<String> moves;

    public Solution() {
        this.stacks = new char[100][];
        this.moves = new ArrayList<>();
        this.parsedStacks = new ArrayList<>();
        readFile();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.parsedStacks.get(0));
    }

    public void readFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(getPath()))) {
            boolean changeList = false;
            String line;
            int count = 0;
            while ((line = br.readLine()) != null) {
                if (line.isEmpty()) {
                    changeList = true;
                    continue;
                }
                if (changeList) {
                    moves.add(line);
                } else {
                    char[] list = new char[100];

                    for (int i = 0; i < line.length(); i++) {
                        list[i] = line.charAt(i);
                        stacks[count] = list;
                    }
                }

                for (int i = 0; i < stacks.length; i++) {
                    List<String> list = new ArrayList<>();
                    for (int j = 0; j < 4; j++) {
                        if (stacks[j][i] != '\u0000') {
                            char c = stacks[j][i];
                            if (Character.isLetter(c)) {
                                list.add(String.valueOf(c));
                            }
                        }
                    }
                    parsedStacks.add(list);
                }
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