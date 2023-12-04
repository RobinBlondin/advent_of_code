package year2023.day4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LotteryTicket {
    private List<String> winningNumbers;
    private List<String> ticketNumbers;
    private int multiplier;
    private int numberOfTicketsWon;
    private int points;

    public LotteryTicket() {
        winningNumbers = new ArrayList<>();
        ticketNumbers = new ArrayList<>();
        multiplier = 1;
        numberOfTicketsWon = 0;
        points = 0;
    }

    public void setLists(String line) {
        String[] arr = String.join(" ", line.split("  ")).split(" \\| ");
        winningNumbers = Arrays.asList(arr[0].split(": ")[1].split(" "));
        ticketNumbers = Arrays.asList(arr[1].split(" "));
    }

    public void setPoints() {
        int points = 0;
        for (String str: winningNumbers) {
            if (ticketNumbers.contains(str)) {
                numberOfTicketsWon++;
                if(points == 0) {
                    points = 1;
                } else {
                    points += points;
                }
            }
        }
        this.points = points;
    }

    public int getPoints() {
        return points * multiplier;
    }

    public int getNumberOfTicketsWon() {
        return numberOfTicketsWon;
    }

    public void addMultiplier() {
        this.multiplier += 1;
    }

    public int getMultiplier() {
        return multiplier;
    }
}
