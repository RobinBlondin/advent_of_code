package year2023.day7;

public enum HandType {
    HIGH_CARD("0"),
    ONE_PAIR("1"),
    TWO_PAIR("2"),
    THREE_OF_A_KIND("3"),
    FULL_HOUSE("4"),
    FOUR_OF_A_KIND("5"),
    FIVE_OF_A_KIND("6");

    private final String value;

    HandType(String value) {
        this.value = value;
    }

    public static int getValue(HandType handType) {
        return Integer.parseInt(handType.value);
    }


}
