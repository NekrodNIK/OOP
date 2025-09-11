package sys.pro;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

/** The player/dealer hand. */
public class Hand {
    private ArrayList<Card> cards = new ArrayList<Card>();
    private Boolean threshold = false;

    /**
     * Add a card in the hand.
     *
     * @param Card
     */
    public void add(Card card) {
        cards.add(card);
    }

    /** Clear the hand. */
    public void clear() {
        threshold = false;
        cards.clear();
    }

    /**
     * Get the total amount of cards in the hand.
     *
     * @return int total amount
     */
    public int getTotal() {
        int total = cards.stream().mapToInt((card) -> card.getPoints(false)).sum();

        threshold = total >= 21;
        if (threshold) total = cards.stream().mapToInt((card) -> card.getPoints(threshold)).sum();
        return total;
    }

    /**
     * Finds any hidden card, if exists.
     *
     * @return Optional<Card> maybe card
     */
    public Optional<Card> findHiddenCard() {
        return cards.stream().filter((c) -> c.hidden).findAny();
    }

    /**
     * Convert card to String
     *
     * @param Card card
     * @return String str
     */
    private String cardToString(Card card) {
        if (card.hidden) {
            return card.toString();
        }
        return "%s (%d)".formatted(card, card.getPoints(threshold));
    }

    /**
     * Convert Hand to String
     *
     * @param Card card
     * @return String str
     */
    @Override
    public String toString() {
        return "[%s] => %d"
                .formatted(
                        cards.stream()
                                .map((card) -> cardToString(card))
                                .collect(Collectors.joining(", ")),
                        getTotal());
    }
}
