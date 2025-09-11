package sys.pro;

/** Playing card representation. */
public class Card {
    /** Card rank. */
    public enum Rank {
        /** TWO */
        TWO("Двойка", 2),
        /** THREE */
        THREE("Тройка", 3),
        /** FOUR */
        FOUR("Четвёрка", 4),
        /** FIVE */
        FIVE("Пятёрка", 5),
        /** SIX */
        SIX("Шестёрка", 6),
        /** SEVEN */
        SEVEN("Семёрка", 7),
        /** EIGHT */
        EIGHT("Восьмёрка", 8),
        /** NINE */
        NINE("Девятка", 9),
        /** TEN */
        TEN("Десятка", 10),
        /** JACK */
        JACK("Валет", 10),
        /** QUEEN */
        QUEEN("Дама", 10),
        /** KING */
        KING("Король", 10),
        /** ACE */
        ACE("Туз", 11);

        /** display label */
        public final String label;

        /** defautl points */
        public final int defaultPoints;

        private Rank(String label, int defaultPoints) {
            this.label = label;
            this.defaultPoints = defaultPoints;
        }
    }

    /** Card suit. */
    public enum Suit {
        /** HEARTS */
        HEARTS("черви"),
        /** DIAMONDS */
        DIAMONDS("бубны"),
        /** CLUBS */
        CLUBS("трефы"),
        /** SPADES */
        SPADES("пики");

        /** display label */
        public final String label;

        private Suit(String label) {
            this.label = label;
        }
    }

    /** rank */
    public Rank rank;

    /** suit */
    public Suit suit;

    /** hidden flag */
    public Boolean hidden;

    /**
     * Card constructor
     *
     * @param rank Card rank
     * @param suit Card suit
     * @param hidden Card hidden flag
     */
    public Card(Rank rank, Suit suit, Boolean hidden) {
        this.rank = rank;
        this.suit = suit;
        this.hidden = hidden;
    }

    /**
     * Get points based on threshold.
     *
     * @param threshold threshold flag
     * @return points card points
     */
    public int getPoints(Boolean threshold) {
        if (hidden) {
            return 0;
        }
        if (this.rank == Rank.ACE && threshold) {
            return 1;
        }
        return this.rank.defaultPoints;
    }

    /** Convert Card to String. */
    @Override
    public String toString() {
        if (hidden) {
            return "<закрытая карта>";
        }
        return String.format("%s %s", this.rank.label, this.suit.label);
    }
}
