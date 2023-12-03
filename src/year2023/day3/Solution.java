package year2023.day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    private final List<List<Character>> grid;
    private final List<Number> numbers;
    private int setPart;

    public Solution() {
        grid = new ArrayList<>();
        numbers = new ArrayList<>();
        readFileToList();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.setPart = 1;
        solution.part1();
        solution.part2();
    }

    public void part1() {
        int sum = 0;
        boolean toBeAdded = false;
        StringBuilder number = new StringBuilder();

        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid.size(); j++) {
                char currentElement = grid.get(i).get(j);
                if (Character.isDigit(currentElement)) {
                    number.append(currentElement);
                    char symbol = setPart == 1 ? '\u0000' : '*';
                    if (isNextToSymbol(i, j, symbol)) {
                        toBeAdded = true;
                    }
                } else {
                    if (toBeAdded) {
                        if (setPart == 1) {
                            sum += Integer.parseInt(number.toString());
                        } else {
                            numbers.get(numbers.size() - 1).setNumber(Integer.parseInt(number.toString()));
                        }
                    }
                    number = new StringBuilder();
                    toBeAdded = false;
                }
            }
        }
        System.out.println("Part 1: " + sum);
    }

    public void part2() {
        int sum = 0;
        for (int i = 0; i < numbers.size(); i++) {
            for (int j = i + 1; j < numbers.size(); j++) {
                if (numbers.get(i).getColumn() == numbers.get(j).getColumn() &&
                        numbers.get(i).getRow() == numbers.get(j).getRow()) {
                    sum += numbers.get(i).getNumber() * numbers.get(j).getNumber();
                }
            }
        }
        System.out.println("Part 2: " + sum);
    }

    public void readFileToList() {
        try (BufferedReader br = new BufferedReader(new FileReader(getPath()))) {
            String line;
            while ((line = br.readLine()) != null) {
                List<Character> row = new ArrayList<>();
                for (char c : line.toCharArray()) {
                    row.add(c);
                }
                grid.add(row);
            }
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }

    public boolean isNextToSymbol(int i, int j, char symbol) {
        int colFrom = Math.max(i - 1, 0);
        int colTo = Math.min(i + 1, grid.size() - 1);
        int rowFrom = Math.max(j - 1, 0);
        int rowTo = Math.min(j + 1, grid.size() - 1);

        for (; colFrom <= colTo; colFrom++) {
            for (; rowFrom <= rowTo; rowFrom++) {
                char currentElement = grid.get(colFrom).get(rowFrom);
                if (symbol == '\u0000') {
                    if (!(Character.isDigit(currentElement)) && currentElement != '.') {
                        return true;
                    }
                } else {
                    if (currentElement == symbol) {
                        numbers.add(new Number(0, colFrom, rowFrom));
                        return true;
                    }
                }
            }
            rowFrom = Math.max(j - 1, 0);
        }
        return false;
    }



        public String getPath () {
            String[] arr = "year2023.day3".split("\\.");
            String year = arr[0].replace("year", "");
            String day = arr[1];
            return String.format("files/%s/%s.txt", year, day);
        }
    }
