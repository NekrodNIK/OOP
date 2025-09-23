package sys.pro;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Test for Hand. */
public class HandTest {
    Hand hand;

    @BeforeEach
    void setUp() {
        hand = new Hand();
    }

    @Test
    void testAdd() {
        Card card = new Card(Rank.ACE, Suit.CLUBS, false);
        hand.add(card);
        assertEquals(card, hand.getLastAddedCard());
    }

    @Test
    void testFindHiddenCard() {
        hand.add(new Card(Rank.SEVEN, Suit.CLUBS, false));
        assertTrue(hand.findHiddenCard().isEmpty());

        Card hidden = new Card(Rank.ACE, Suit.CLUBS, true);
        hand.add(hidden);
        assertEquals(hidden, hand.findHiddenCard().get());
    }

    @Test
    void testGetTotalTreshold() {
        hand.add(new Card(Rank.ACE, Suit.CLUBS, false));
        hand.add(new Card(Rank.ACE, Suit.DIAMONDS, false));

        assertEquals(2, hand.getTotal());
    }

    @Test
    void testClear() {
        hand.add(new Card(Rank.ACE, Suit.CLUBS, false));
        hand.add(new Card(Rank.QUEEN, Suit.DIAMONDS, false));
        hand.clear();
        assertEquals(0, hand.size());
        assertEquals(0, hand.getTotal());
    }

    @Test
    void testToString() {
        hand.add(new Card(Rank.ACE, Suit.CLUBS, false));
        Card card = hand.getLastAddedCard();

        assertTrue(hand.toString().contains(card.getRank().getLabel()));
        assertTrue(hand.toString().contains(card.getSuit().getLabel()));

        card.hide();
        assertFalse(hand.toString().contains(card.getRank().getLabel()));
        assertFalse(hand.toString().contains(card.getSuit().getLabel()));
    }

    @Test
    void testCopy() {
        Hand copy = new Hand(hand);

        assertNotSame(hand, copy);
        assertEquals(hand, copy);
    }

    @Test
    void testEquals() {
        assertEquals(hand, hand);
        assertNotEquals(hand, new Object());
    }
}
