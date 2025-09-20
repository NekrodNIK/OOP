package sys.pro;

/** Playing card representation. */
public class Card {
    /** rank. */
    private Rank rank;

    /** suit. */
    private Suit suit;

    /** hidden flag */
    private Boolean hidden;

    /**
     * Card constructor.
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
        return this.rank.getDefaultPoints();
    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }

    public Boolean isHidden() {
        return hidden;
    }

    public void hide() {
        hidden = true;
    }

    public void show() {
        hidden = false;
    }

    /** Convert Card to String. */
    @Override
    public String toString() {
        if (hidden) {
            return "<закрытая карта>";
        }
        return String.format("%s %s", this.rank.getLabel(), this.suit.getLabel());
    }
}
