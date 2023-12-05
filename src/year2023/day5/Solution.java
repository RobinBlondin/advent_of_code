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
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
       solution.part1();
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
        for (int i = 0; i < seeds.length; i+=2) {
            for (long j = seeds[i]; j < seeds[i]+seeds[i+1]; j++) {
                newSeeds.add(j);
            }
        }
    }

    public String getPath() {
        String[] arr = "year2023.day5".split("\\.");
        String year = arr[0].replace("year", "");
        String day = arr[1];
        return String.format("files/%s/%s.txt", year, day);
    }
}