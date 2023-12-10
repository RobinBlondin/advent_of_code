package year2023.day7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
    private final List<Hand> hands;

    public Solution() {
        hands = new ArrayList<>();
        readFile();
    }
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.part2();
    }

    public void part2() {
        long sum = 0;

        for (int i = 0; i < hands.size(); i++) {
            for (int j = i + 1; j < hands.size(); j++) {
                Hand hand1 = hands.get(i);
                Hand hand2 = hands.get(j);

                Hand bestHand = hand1.compare(hand2);

                if (bestHand == hand2) {
                    hands.set(i, hand2);
                    hands.set(j, hand1);
                }
            }
        }
        Collections.reverse(hands);
        for (int i = 0; i < hands.size(); i++) {
            sum += hands.get(i).getBid() * (i + 1);
        }
        System.out.println(sum);
    }

    public void readFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(getPath()))) {
            String line;
            while ((line = br.readLine()) != null) {
                hands.add(new Hand(line));
            }
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }

    public String getPath() {
        String[] arr = "year2023.day7".split("\\.");
        String year = arr[0].replace("year", "");
        String day = arr[1];
        return String.format("files/%s/%s.txt", year, day);
    }
}