package year2023.day5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Solution {
    private long[] seeds;
    private final List<Long> newSeeds;
    private final List<String> converter;

    Stages stage;

    public Solution() {
        converter = new ArrayList<>();
        newSeeds = new ArrayList<>();
        stage = Stages.SEEDS;

        readFile();
        setNewSeeds();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        List<Long> results = new ArrayList<>();

            long min = 1;
            long max = 1100;

            while(min <= max) {
                long mid = min + (max - min) / 2;
                if (solution.part2(mid)) {
                    results.add(mid);
                    max = mid - 1;
                } else {
                    min = mid + 1;
                }
            }

        System.out.println(results);
    }

    public void part1() {
        List<Integer> changedValues = new ArrayList<>();
        for (String s : converter) {
            if (s.isEmpty()){
                continue;
            } else if (Character.isLetter(s.charAt(0))) {
                changedValues.clear();
                setStage(s);
                continue;
            }
            long[] longs = Arrays.stream(s.split(" ")).mapToLong(Long::parseLong).toArray();

            for (int j = 0; j < seeds.length; j++) {
                if (!(changedValues.contains(j)) && (seeds[j] >= longs[1] && seeds[j] < longs[1] + longs[2])) {
                    seeds[j] = (seeds[j] - longs[1]) + (longs[0]);
                    changedValues.add(j);
                }
            }
        }
        long min = Arrays.stream(seeds).min().getAsLong();
        System.out.println(min);
    }

    public boolean part2(Long input) {
        boolean found = false;
        boolean foundMatch = false;

            long number = input;
            for (int i = converter.size() - 1; i >= 0; i--) {

                if (converter.get(i).isEmpty()) {
                    continue;
                } else if (Character.isLetter(converter.get(i).charAt(0))) {
                    setStage(converter.get(i));
                    found = false;
                    continue;
                } else if (found) {
                    continue;
                }

                long[] longs = Arrays.stream(converter.get(i).split(" ")).mapToLong(Long::parseLong).toArray();

                if (number < longs[0] || number - longs[0] + longs[1] >= longs[1] + longs[2]) {
                    continue;
                }

                number = number - longs[0] + longs[1];
                found = true;

                if (stage == Stages.FERTILIZER) {
                    for (int j = 0; j < seeds.length - 1; j += 2) {
                        if (number >= newSeeds.get(j) && number < newSeeds.get(j + 1)) {
                            System.out.println("found match: " + input);
                            return true;
                        }
                    }
                }
            }
            System.out.println("no match found: ");
            return false;
    }

    public void readFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(getPath()))) {
            String line;
            seeds = Arrays.stream(br.readLine().split("seeds: ")[1].split(" ")).mapToLong(Long::parseLong).toArray();
            while ((line = br.readLine()) != null) {
                converter.add(line);
            }
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }

    public void setStage(String str) {
        String stage = str.split(" ")[0].split("-")[2];
        this.stage = Stages.getStage(stage);
    }

    public void setNewSeeds() {
        for (int i = 0; i < seeds.length - 1; i+=2) {
            newSeeds.add(seeds[i]);
            newSeeds.add(seeds[i] + seeds[i+1] - 1);
        }
    }

    public String getPath() {
        String[] arr = "year2023.day5".split("\\.");
        String year = arr[0].replace("year", "");
        String day = arr[1];
        return String.format("files/%s/%s.txt", year, day);
    }
}