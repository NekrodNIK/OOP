package sys.pro;

public enum Grade {
    EXCELLENT(5),
    GOOD(4),
    SATISFACTORY(3);

    private final int value;

    Grade(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
