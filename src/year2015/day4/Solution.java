package year2015.day4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Solution {
    public static void main(String[] args) {
        new Solution().solution();
    }

    public void solution() {
        try (BufferedReader br = new BufferedReader(new FileReader(getPath()))) {
            String input = br.readLine();
            //--- End of parsing file ---//
            int answer = 0;
            for (int i = 1; i < Integer.MAX_VALUE; i++) {
                String str = input + i;

                //--- Convert string to MD5 to hexadecimal string ---//
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(str.getBytes(StandardCharsets.UTF_8));
                byte[] digest = md.digest();
                String hex = String.format("%032x", new BigInteger(1, digest));
                //--- End of conversion ---//

                if (hex.startsWith("000000")){
                    answer = i;
                    break;
                }
            }
            System.out.println(answer);
        } catch (IOException e) {
            System.out.println("File not found");
        } catch (NoSuchAlgorithmException e) {
            System.out.println("No such algorithm");
        }
    }

    public String getPath() {
        String[] arr = "year2015.day4".split("\\.");
        String year = arr[0].replace("year", "");
        String day = arr[1];
        return String.format("files/%s/%s.txt", year, day);
    }
}