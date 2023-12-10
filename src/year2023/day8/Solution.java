package year2023.day8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Solution {
    List<String> turns;
    List<String> startPoints;
    List<Integer> ranges;
    Map<String, String> map;


    public Solution() {
        turns = new ArrayList<>();
        startPoints = new ArrayList<>();
        ranges = new ArrayList<>();
        map = new HashMap<>();
        readFile();
    }
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.part1();
        solution.part2();
    }

    public void part1() {
        String currentLocation = "AAA";
        int i = 0;
        while(!currentLocation.equals("ZZZ")) {
            int turnIndex = i % turns.size();
            String turn = turns.get(turnIndex);

            if(turn.equals("L")) {
                currentLocation = map.get(currentLocation).split(" ")[0];
            } else if(turn.equals("R")) {
                currentLocation = map.get(currentLocation).split(" ")[1];
            }
            i++;
        }
        System.out.println("Part 1: " + i);
    }

    public void part2(){
        for(String startPoint : startPoints) {
            String currentLocation = startPoint;
            int i = 0;
            while(!currentLocation.endsWith("Z")) {
                long turnIndex = i % turns.size();
                String turn = turns.get((int) turnIndex);

                if(turn.equals("L")) {
                    currentLocation = map.get(currentLocation).split(" ")[0];
                } else if(turn.equals("R")) {
                    currentLocation = map.get(currentLocation).split(" ")[1];
                }
                i++;
            }
            ranges.add(i);
        }
        System.out.println("Part 2: "+ findLCM(ranges));
    }

    public long findLCM(List<Integer> list) {
        long result = list.getFirst();
        for(int i = 1; i < list.size(); i++) {
            result = lcm(result, list.get(i));
        }
        return result;
    }

    public long lcm(long a, long b) {
        return a * (b / gcd(a, b));
    }

    public long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public void readFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(getPath()))) {
            String line = br.readLine();
            turns = List.of(line.split(""));
            while ((line = br.readLine()) != null) {
                if (line.isEmpty()) {
                    continue;
                }
                String[] arr = line.split(" = ");
                String keyPart = arr[0];

                if (keyPart.endsWith("A")) {
                    startPoints.add(keyPart);
                }

                String valuePart = arr[1].replace("(", "").replace(")", "").replace(",", "");
                map.put(keyPart, valuePart);

            }
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }

    public String getPath() {
        String[] arr = "year2023.day8".split("\\.");
        String year = arr[0].replace("year", "");
        String day = arr[1];
        return String.format("files/%s/%s.txt", year, day);
    }
}