package sys.pro;

/** Grade. */
public enum Grade {
    /** EXCELLENT. */
    EXCELLENT(5),
    /** GOOD. */
    GOOD(4),
    /** SATISFACTORY. */
    SATISFACTORY(3);

    private final int value;

    private Grade(int value) {
        this.value = value;
    }

    /**
     * Get value.
     *
     * @return value
     */
    public int getValue() {
        return value;
    }
}
