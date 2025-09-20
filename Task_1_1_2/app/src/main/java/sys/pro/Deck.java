package sys.pro;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class Deck {
    private ArrayList<Card> initial;
    private ArrayList<Card> arr;

    public Deck(Collection<Card> initial) {
        this.initial = new ArrayList<Card>(initial);
        arr = new ArrayList<Card>(initial);
    }

    public Deck() {
        this(
                Stream.of(Rank.values())
                        .flatMap(
                                rank ->
                                        Stream.of(Suit.values())
                                                .map(suit -> new Card(rank, suit, true)))
                        .toList());
    }

    public void shuffle() {
        Collections.shuffle(arr);
    }

    public void restore() {
        arr.clear();
        arr.addAll(initial);
    }

    public void dealFaceUp(Hand hand) {
        Card card = arr.removeLast();
        card.show();
        hand.add(card);
    }

    public void dealFaceDown(Hand hand) {
        Card card = arr.removeLast();
        card.hide();
        hand.add(card);
    }

    public List<Card> toList() {
        return Collections.unmodifiableList(arr);
    }

    public int size() {
        return arr.size();
    }
}
