package year2022.day2;

public enum Values {
    ROCK(1, "A", "X", "Y", "Z"),
    PAPER(2, "B", "Y", "Z", "X"),
    SCISSORS(3, "C", "Z", "X", "Y"),
    NONE(0, null, null, null, null);


    private final int points;
    private final String opponentIdentifier;
    private final String playerIdentifier;
    private final String beatenBy;
    private final String beats;

    Values(int points, String opponentIdentifier, String playerIdentifier, String beatenBy, String beats) {
        this.points = points;
        this.opponentIdentifier = opponentIdentifier;
        this.playerIdentifier = playerIdentifier;
        this.beatenBy = beatenBy;
        this.beats = beats;
    }

    public int getPoints() {
        return points;
    }

    public String getOpponentIdentifier() {
        return opponentIdentifier;
    }

    public String getPlayerIdentifier() {
        return playerIdentifier;
    }

    public String getBeatenBy() {
        return beatenBy;
    }

    public String getBeats() {
        return beats;
    }

    public static Values getEnum(String value) {
        try {
            for (Values v : values())
                if (v.getOpponentIdentifier().equalsIgnoreCase(value) || v.getPlayerIdentifier().equalsIgnoreCase(value)) {
                    return v;
                }
        } catch (Exception e) {
            System.out.println("Invalid value");
        }
        return NONE;
    }
}
