package sys.pro;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

/** Deck representation. */
public class Deck {
    private ArrayList<Card> initial;
    private ArrayList<Card> arr;

    /**
     * Constructor.
     *
     * @param initial Initial state
     */
    public Deck(Collection<Card> initial) {
        this.initial = new ArrayList<Card>(initial);
        arr = new ArrayList<Card>(initial);
    }

    /** Default constructor. */
    public Deck() {
        this(
                Stream.of(Rank.values())
                        .flatMap(
                                rank ->
                                        Stream.of(Suit.values())
                                                .map(suit -> new Card(rank, suit, true)))
                        .toList());
    }

    /** Shuffle the Deck. */
    public void shuffle() {
        Collections.shuffle(arr);
    }

    /** Restore initial state of Deck. */
    public void restore() {
        arr.clear();
        arr.addAll(initial);
    }

    /**
     * Deal a Card face up into a Hand.
     *
     * @param hand Some Hand
     */
    public void dealFaceUp(Hand hand) {
        Card card = arr.removeLast();
        card.show();
        hand.add(card);
    }

    /**
     * Deal a Card face down into a Hand.
     *
     * @param hand Some Hand
     */
    public void dealFaceDown(Hand hand) {
        Card card = arr.removeLast();
        card.hide();
        hand.add(card);
    }

    /**
     * Returns an immutable list representation of the Deck.
     *
     * @return list
     */
    public List<Card> toList() {
        return Collections.unmodifiableList(arr);
    }

    /**
     * Return size of Deck.
     * @return size
     */
    public int size() {
        return arr.size();
    }
}
