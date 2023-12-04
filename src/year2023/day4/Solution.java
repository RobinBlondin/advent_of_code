package year2023.day4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    List<LotteryTicket> tickets;

    public Solution() {
        tickets = new ArrayList<>();
    }
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.solution();
        solution.part1();
        solution.part2();
    }

    public void solution() {
        try (BufferedReader br = new BufferedReader(new FileReader(getPath()))) {
            String line;
            while ((line = br.readLine()) != null) {
                LotteryTicket ticket = new LotteryTicket();
                ticket.setLists(line);
                ticket.setPoints();
                System.out.println("points after set: " + ticket.getPoints());
                System.out.println("number of tickets won: " + ticket.getNumberOfTicketsWon());
                tickets.add(ticket);
            }
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }

    public void part1() {
        int sum = 0;
        for (LotteryTicket ticket : tickets) {
            sum += ticket.getPoints();
        }
        System.out.println(sum);
    }

    public void part2() {
        int sum = 0;
        int count = 0;
        for (LotteryTicket ticket : tickets) {
            sum += ticket.getMultiplier();
            int iterations = ticket.getNumberOfTicketsWon();

            for (int i = count + 1; i <= count + iterations; i++) {
                for (int j = 0; j < ticket.getMultiplier(); j++) {
                    tickets.get(i).addMultiplier();
                }
            }
            count++;
        }
        System.out.println(sum);
    }

    public String getPath() {
        String[] arr = "year2023.day4".split("\\.");
        String year = arr[0].replace("year", "");
        String day = arr[1];
        return String.format("files/%s/%s.txt", year, day);
    }
}