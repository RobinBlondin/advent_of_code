package year2023.day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    Map<String, Integer> compareMap;
    Map<String, Integer> gameResults;

    public Solution() {
        compareMap = new HashMap<>();
        compareMap.put("red", 12);
        compareMap.put("green", 13);
        compareMap.put("blue", 14);

        gameResults = new HashMap<>();
        gameResults.put("red", 0);
        gameResults.put("green", 0);
        gameResults.put("blue", 0);
    }

    public static void main(String[] args) {
        new Solution().solution();
    }

    public void solution() {
        try (BufferedReader br = new BufferedReader(new FileReader(getPath()))) {
            int sum = 0;
            String line;
            while ((line = br.readLine()) != null) {
                int gameID = getGameID(line);
                line = removeGameID(line);

                // Part 1
               /* if(gameIsPossible(line)){
                    sum += gameID;
                }*/

                // Part 2
                getLowestAmountOfCubes(line);


                sum += gameResults.values().stream().reduce(1, (a, b) -> a * b);
                gameResults.replaceAll((k, v) -> 0);
            }
            System.out.println(sum);
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }

    public String getPath() {
        String path = "files/year2023.day2.txt";
        return path.replace("year2023.", "");
    }

    public int getGameID(String line) {
        return Integer.parseInt(line.split(": ")[0].split(" ")[1]);
    }

    public String removeGameID(String line) {
        return line.split(": ")[1];
    }

    public boolean gameIsPossible(String line) {
        for (String str : line.split("; ")) {
            for (String round : str.split(", ")) {
                String[] temp = round.split(" ");
                gameResults.put(temp[1], gameResults.get(temp[1]) + Integer.parseInt(temp[0]));
                if (!isPossible()) {
                    return false;
                }
                gameResults.replaceAll((k, v) -> 0);
            }
        }
        return true;
    }

    public boolean isPossible() {
        return (gameResults.get("red") <= compareMap.get("red") &&
                gameResults.get("green") <= compareMap.get("green") &&
                gameResults.get("blue") <= compareMap.get("blue"));
    }

    public void getLowestAmountOfCubes(String line) {
        for (String str : line.split("; ")) {
            for (String round : str.split(", ")) {
                String[] temp = round.split(" ");
                if (gameResults.get(temp[1]) == 0) {
                    gameResults.put(temp[1], gameResults.get(temp[1]) + Integer.parseInt(temp[0]));
                } else if (Integer.parseInt(temp[0]) > gameResults.get(temp[1])) {
                    gameResults.put(temp[1], Integer.parseInt(temp[0]));
                }
            }
        }
    }
}