package sys.pro;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

/** Test for App. */
public class AppTest {
    @Test
    void testNextRound() {
        ArrayList<Card> initialDeck = new ArrayList<Card>();
        initialDeck.add(new Card(Rank.KING, Suit.HEARTS, true));
        initialDeck.add(new Card(Rank.FIVE, Suit.CLUBS, true));
        initialDeck.add(new Card(Rank.FIVE, Suit.SPADES, true));
        initialDeck.add(new Card(Rank.FIVE, Suit.HEARTS, true));
        initialDeck.add(new Card(Rank.FIVE, Suit.DIAMONDS, true));

        Deck deck = new Deck(initialDeck, true);
        Game game = new Game(deck);
        Scanner input = new Scanner("1\n0\n");

        assertTrue(App.nextRound(game, input));
        assertEquals(1, game.getPlayerScore());
        assertEquals(0, game.getDealerScore());
    }

    @Test
    void testNextRoundBlackJack() {
        ArrayList<Card> initialDeck = new ArrayList<Card>();
        initialDeck.add(new Card(Rank.FIVE, Suit.SPADES, true));
        initialDeck.add(new Card(Rank.FIVE, Suit.HEARTS, true));
        initialDeck.add(new Card(Rank.ACE, Suit.DIAMONDS, true));
        initialDeck.add(new Card(Rank.KING, Suit.HEARTS, true));

        Deck deck = new Deck(initialDeck, true);
        Game game = new Game(deck);
        Scanner input = new Scanner("");

        assertTrue(App.nextRound(game, input));
        assertTrue(game.isBlackJack());
        assertEquals(1, game.getPlayerScore());
        assertEquals(0, game.getDealerScore());
    }

    @Test
    void testNextRoundDealerVictory() {
        ArrayList<Card> initialDeck = new ArrayList<Card>();
        initialDeck.add(new Card(Rank.KING, Suit.HEARTS, true));
        initialDeck.add(new Card(Rank.FIVE, Suit.CLUBS, true));
        initialDeck.add(new Card(Rank.FIVE, Suit.SPADES, true));
        initialDeck.add(new Card(Rank.FIVE, Suit.HEARTS, true));
        initialDeck.add(new Card(Rank.FIVE, Suit.DIAMONDS, true));

        Deck deck = new Deck(initialDeck, true);
        Game game = new Game(deck);
        Scanner input = new Scanner("0\n");

        assertFalse(App.nextRound(game, input));
        assertEquals(0, game.getPlayerScore());
        assertEquals(1, game.getDealerScore());
    }

    @Test
    void testNextRoundDraw() {
        ArrayList<Card> initialDeck = new ArrayList<Card>();
        initialDeck.add(new Card(Rank.KING, Suit.HEARTS, true));
        initialDeck.add(new Card(Rank.FIVE, Suit.CLUBS, true));
        initialDeck.add(new Card(Rank.FIVE, Suit.SPADES, true));
        initialDeck.add(new Card(Rank.FIVE, Suit.HEARTS, true));
        initialDeck.add(new Card(Rank.FIVE, Suit.DIAMONDS, true));

        Deck deck = new Deck(initialDeck, true);
        Game game = new Game(deck);
        Scanner input0 = new Scanner("0\n");
        Scanner input1 = new Scanner("1\n0\n");

        assertFalse(App.nextRound(game, input0));
        assertTrue(App.nextRound(game, input1));
        assertEquals(1, game.getPlayerScore());
        assertEquals(1, game.getDealerScore());
    }
}
