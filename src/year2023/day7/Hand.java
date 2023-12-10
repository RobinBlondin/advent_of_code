package year2023.day7;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Hand {

    private String hand;
    private long bid;
    private HandType handType;


    public Hand(String hand) {
        this.hand = hand.split(" ")[0].toUpperCase();
        this.bid = Integer.parseInt(hand.split(" ")[1]);
        setHandType();
    }

    public void setHandType() {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : hand.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int numberOfJokers = map.getOrDefault('J', 0);

        char keyOfMaxValue = Collections.max(map.entrySet(), Map.Entry.comparingByValue()).getKey();
        if(numberOfJokers != 0) {
            char highest = getHighestCard(hand);

            if(keyOfMaxValue == 'J' || map.get(keyOfMaxValue) < 2) {
                map.put(highest, map.get(highest) + numberOfJokers);
            } else {
                map.put(keyOfMaxValue, map.getOrDefault(keyOfMaxValue, 0) + numberOfJokers);
            }
            map.remove('J');
        }

        if (numberOfJokers == 5 || map.containsValue(5)) {
            handType = HandType.FIVE_OF_A_KIND;
        } else if (map.containsValue(4)) {
            handType = HandType.FOUR_OF_A_KIND;
        } else if (map.containsValue(3) && map.containsValue(2)) {
            handType = HandType.FULL_HOUSE;
        } else if (map.containsValue(3)) {
            handType = HandType.THREE_OF_A_KIND;
        } else if (map.containsValue(2) && map.size() == 3) {
            handType = HandType.TWO_PAIR;
        } else if (map.containsValue(2)) {
            handType = HandType.ONE_PAIR;
        } else {
            handType = HandType.HIGH_CARD;
        }
    }

    public char getHighestCard(String hand) {
        char highest = 'J';
        for (char c : hand.toCharArray()) {
            if (CardValues.getNumber(String.valueOf(c)) > CardValues.getNumber(String.valueOf(highest))) {
                highest = c;
            }
        }
        return highest;
    }

    public Hand compare(Hand other) {
        if (HandType.getValue(this.handType) > HandType.getValue(other.handType)) {
            return this;
        } else if (HandType.getValue(this.handType) < HandType.getValue(other.handType)) {
            return other;
        } else {
            int thisCardValue = 0;
            int otherCardValue = 0;
            for (int i = 0; i < this.hand.length(); i++) {
                thisCardValue = CardValues.getNumber(String.valueOf(hand.charAt(i)));
                otherCardValue = CardValues.getNumber(String.valueOf(other.hand.charAt(i)));

                if (thisCardValue > otherCardValue) {
                    return this;
                } else if (thisCardValue < otherCardValue) {
                    return other;
                }
            }
        }
        return this;
    }

    public String getHand() {
        return hand;
    }

    public void setHand(String hand) {
        this.hand = hand;
    }

    public long getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public HandType getHandType() {
        return handType;
    }

    public void setHandType(HandType handType) {
        this.handType = handType;
    }

    @Override
    public String toString() {
        return "Hand{" +
                "hand='" + hand + '\'' +
                ", bid=" + bid +
                ", handType=" + handType +
                '}';
    }
}
