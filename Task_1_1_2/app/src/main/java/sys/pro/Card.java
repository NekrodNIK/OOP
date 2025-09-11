package sys.pro;

/** Playing card representation. */
public class Card {
    /** Card rank. */
    public enum Rank {
        TWO("Двойка", 2),
        THREE("Тройка", 3),
        FOUR("Четвёрка", 4),
        FIVE("Пятёрка", 5),
        SIX("Шестёрка", 6),
        SEVEN("Семёрка", 7),
        EIGHT("Восьмёрка", 8),
        NINE("Девятка", 9),
        TEN("Десятка", 10),
        JACK("Валет", 10),
        QUEEN("Дама", 10),
        KING("Король", 10),
        ACE("Туз", 11);

        public final String label;
        public final int default_points;

        private Rank(String label, int default_points) {
            this.label = label;
            this.default_points = default_points;
        }
    }

    /** Card suit. */
    public enum Suit {
        HEARTS("черви"),
        DIAMONDS("бубны"),
        CLUBS("трефы"),
        SPADES("пики");

        public final String label;

        private Suit(String label) {
            this.label = label;
        }
    }

    public Rank rank;
    public Suit suit;
    public Boolean hidden;

    /**
     * Card.
     *
     * @param Rank rank
     * @param Suit suit
     * @param Boolean hidden
     */
    public Card(Rank rank, Suit suit, Boolean hidden) {
        this.rank = rank;
        this.suit = suit;
        this.hidden = hidden;
    }

    /**
     * Get points based on threshold.
     *
     * @param Boolean hidden
     * @return int points
     */
    public int getPoints(Boolean threshold) {
        if (hidden) return 0;
        if (this.rank == Rank.ACE && threshold) return 1;
        return this.rank.default_points;
    }

    /**
     * Convert Card to String
     *
     * @return String str
     */
    @Override
    public String toString() {
        if (hidden) return "<закрытая карта>";
        return String.format("%s %s", this.rank.label, this.suit.label);
    }
}
