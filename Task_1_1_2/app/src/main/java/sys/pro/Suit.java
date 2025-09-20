package sys.pro;

/** Card suit. */
public enum Suit {
    /** HEARTS. */
    HEARTS("черви"),
    /** DIAMONDS. */
    DIAMONDS("бубны"),
    /** CLUBS. */
    CLUBS("трефы"),
    /** SPADES. */
    SPADES("пики");

    private final String label;

    private Suit(String label) {
        this.label = label;
    }

    /** Returns label. */
    public String getLabel() {
        return label;
    }
}
