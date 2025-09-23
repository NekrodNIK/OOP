package sys.pro;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/** Test for Card. */
public class CardTest {
    @Test
    void testToString() {
        Card card = new Card(Rank.ACE, Suit.CLUBS, false);
        assertEquals("Туз трефы", card.toString());
        card.hide();
        assertEquals("<закрытая карта>", card.toString());
    }

    @Test
    void testGetPoints() {
        Card card = new Card(Rank.QUEEN, Suit.CLUBS, true);
        assertEquals(0, card.getPoints(false));
        assertEquals(0, card.getPoints(true));

        card.show();
        assertEquals(10, card.getPoints(false));
        assertEquals(10, card.getPoints(true));
    }

    @Test
    void testGetPointsAce() {
        Card card = new Card(Rank.ACE, Suit.CLUBS, false);
        assertEquals(11, card.getPoints(false));
        assertEquals(1, card.getPoints(true));
    }

    @Test
    void testGetSuit() {
        Card card = new Card(Rank.ACE, Suit.CLUBS, false);
        assertEquals(Rank.ACE, card.getRank());
        assertEquals(Suit.CLUBS, card.getSuit());
    }
}
