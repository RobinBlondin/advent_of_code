package year2023.day7;

public enum CardValues {
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("T", 10),
    JACK("J", 1),
    QUEEN("Q", 12),
    KING("K", 13),
    ACE("A", 14);

    private final String value;
    private final int number;

    CardValues(String value, int number) {
        this.value = value;
        this.number = number;
    }

    public static int getValue(CardValues cardValues) {
        return cardValues.number;
    }

    public static int getNumber(String value) {
        for (CardValues cardValues : CardValues.values()) {
            if (cardValues.value.equals(value)) {
                return cardValues.number;
            }
        }
        return -1;
    }
}
