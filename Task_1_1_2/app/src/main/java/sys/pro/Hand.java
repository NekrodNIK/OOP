package sys.pro;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

/** The player/dealer hand. */
public class Hand {
    private ArrayList<Card> cards;
    private Boolean threshold;

    public Hand() {
        cards = new ArrayList<Card>();
        threshold = false;
    }

    public Hand(Hand src) {
        cards = new ArrayList<Card>(src.cards);
        threshold = src.threshold;
    }

    /**
     * Add a card in the hand.
     *
     * @param card a card
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
     * @return amount
     */
    public int getTotal() {
        int total = cards.stream().mapToInt((card) -> card.getPoints(false)).sum();

        threshold = total > 21;
        if (threshold) {
            total = cards.stream().mapToInt((card) -> card.getPoints(threshold)).sum();
        }
        return total;
    }

    /**
     * Finds any hidden card, if exists.
     *
     * @return maybe
     */
    public Optional<Card> findHiddenCard() {
        return cards.stream().filter((c) -> c.isHidden()).findAny();
    }

    public Card getLastAddedCard() {
        return cards.getLast();
    }

    public int size() {
        return cards.size();
    }

    /** Convert Card to String. */
    private String cardToString(Card card) {
        if (card.isHidden()) {
            return card.toString();
        }
        return "%s (%d)".formatted(card, card.getPoints(threshold));
    }

    /** Convert Hand to String. */
    @Override
    public String toString() {
        return "[%s] => %d"
                .formatted(
                        cards.stream()
                                .map((card) -> cardToString(card))
                                .collect(Collectors.joining(", ")),
                        getTotal());
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Hand other) {
            return other.cards.equals(this.cards);
        }

        return false;
    }
}
