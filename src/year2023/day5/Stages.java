package year2023.day5;

public enum Stages {
    SEEDS("seed"),
    SOIL("soil"),
    FERTILIZER("fertilizer"),
    WATER("water"),
    LIGHT("light"),
    TEMPERATURE("temperature"),
    HUMIDITY("humidity"),
    LOCATION("location");

    private final String stage;

     Stages(String stage) {
        this.stage = stage;
    }
    public static Stages getStage(String stage) {
        for (Stages s : Stages.values()) {
            if (s.stage.equals(stage)) {
                return s;
            }
        }
        return null;
    }

}
