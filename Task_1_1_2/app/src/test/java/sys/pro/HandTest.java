package sys.pro;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HandTest {
    Hand hand;

    @BeforeEach
    void setUp() {
        hand = new Hand();
    }

    @Test
    void testAdd() {
        Card card1 = new Card(Card.Rank.ACE, Card.Suit.CLUBS, false);
        Card card2 = new Card(Card.Rank.SEVEN, Card.Suit.CLUBS, false);
        Card hidden = new Card(Card.Rank.EIGHT, Card.Suit.CLUBS, true);

        hand.add(card1);
        hand.add(card2);
        hand.add(hidden);

        assertEquals(
                "[%s (%d), %s (%d), %s] => %d"
                        .formatted(
                                card1,
                                card1.getPoints(false),
                                card2,
                                card2.getPoints(false),
                                hidden,
                                card1.getPoints(false) + card2.getPoints(false)),
                hand.toString());
    }

    @Test
    void testFindHiddenCard() {
        hand.add(new Card(Card.Rank.SEVEN, Card.Suit.CLUBS, false));
        assertTrue(hand.findHiddenCard().isEmpty());

        Card hidden = new Card(Card.Rank.ACE, Card.Suit.CLUBS, true);
        hand.add(hidden);
        assertEquals(hidden, hand.findHiddenCard().get());
    }

    @Test
    void testGetTotalTreshold() {
        hand.add(new Card(Card.Rank.ACE, Card.Suit.CLUBS, false));
        hand.add(new Card(Card.Rank.ACE, Card.Suit.DIAMONDS, false));

        assertEquals(2, hand.getTotal());
    }

    @Test
    void testClear() {
        hand.add(new Card(Card.Rank.ACE, Card.Suit.CLUBS, false));
        hand.add(new Card(Card.Rank.QUEEN, Card.Suit.DIAMONDS, false));
        hand.clear();

        assertEquals("[] => 0", hand.toString());
    }
}
