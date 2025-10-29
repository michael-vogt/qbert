package qbert.level;

public enum Neighbors {

    NEIGHBOR_UPPER_LEFT(1),
    NEIGHBOR_UPPER_RIGHT(2),
    NEIGHBOR_LOWER_LEFT(3),
    NEIGHBOR_LOWER_RIGHT(4);

    public final int intValue;

    Neighbors(int value) { intValue = value; }

    public String toString() {
        return switch (this) {
            case NEIGHBOR_UPPER_LEFT -> "nul";
            case NEIGHBOR_UPPER_RIGHT -> "nur";
            case NEIGHBOR_LOWER_LEFT -> "nll";
            case NEIGHBOR_LOWER_RIGHT -> "nlr";
        };
    }

    public String toArrow() {
        return switch (this) {
            case NEIGHBOR_UPPER_LEFT -> "↖";
            case NEIGHBOR_UPPER_RIGHT -> "↗";
            case NEIGHBOR_LOWER_LEFT -> "↙";
            case NEIGHBOR_LOWER_RIGHT -> "↘";
        };
    }

}
