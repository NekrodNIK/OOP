package sys.pro;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CardTest {
    @Test
    void testToString() {
        Card card = new Card(Card.Rank.ACE, Card.Suit.CLUBS, false);
        assertEquals("Туз трефы", card.toString());
        card.hidden = true;
        assertEquals("<закрытая карта>", card.toString());
    }

    @Test
    void testGetPoints() {
        Card card = new Card(Card.Rank.QUEEN, Card.Suit.CLUBS, true);
        assertEquals(0, card.getPoints(false));
        assertEquals(0, card.getPoints(true));

        card.hidden = false;
        assertEquals(10, card.getPoints(false));
        assertEquals(10, card.getPoints(true));
    }

    @Test
    void testGetPointsAce() {
        Card card = new Card(Card.Rank.ACE, Card.Suit.CLUBS, false);
        assertEquals(11, card.getPoints(false));
        assertEquals(1, card.getPoints(true));
    }
}
