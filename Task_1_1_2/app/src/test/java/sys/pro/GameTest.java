package sys.pro;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collections;

/** Test for Game. */
public class GameTest {
    ArrayList<Card> initial;

    @BeforeEach
    void setUp() {
        initial = new ArrayList<Card>();
        initial.add(new Card(Rank.FIVE, Suit.DIAMONDS, false));
        initial.add(new Card(Rank.JACK, Suit.CLUBS, false));
        initial.add(new Card(Rank.ACE, Suit.CLUBS, false));
    }

    @Test
    void testDefaultConstructor() {
        new Game();
    }

    @Test
    void testDealStartingCards() {
        initial.add(new Card(Rank.FIVE, Suit.CLUBS, false));
        Game game = new Game(new Deck(initial));

        game.dealStartingCards();

        assertEquals(2, game.getDealerHand().size());
        assertEquals(2, game.getPlayerHand().size());
    }

    @Test
    void testPlayerBlackJack() {
        initial.add(new Card(Rank.QUEEN, Suit.CLUBS, false));
        Game game = new Game(new Deck(initial));

        game.dealStartingCards();

        assertEquals(21, game.getPlayerPoints());
        assertEquals(State.BLACKJACK_WIN, game.getState());

        game.updateScore();
        assertEquals(1, game.getPlayerScore());
        assertEquals(0, game.getDealerScore());
    }

    @Test
    void testPlayerNotBlackJack() {
        initial.add(new Card(Rank.FIVE, Suit.CLUBS, false));
        Game game = new Game(new Deck(initial));

        game.dealStartingCards();

        assertNotEquals(21, game.getPlayerPoints());
        assertEquals(State.START, game.getState());
    }

    @Test
    void testDealToPlayer() {
        initial.add(new Card(Rank.FIVE, Suit.CLUBS, false));
        initial.add(new Card(Rank.FIVE, Suit.SPADES, false));
        Game game = new Game(new Deck(initial));

        game.nextRound();

        game.dealToPlayer();
        assertTrue(initial.contains(game.getPlayerHand().getLastAddedCard()));
    }

    @Test
    void testPlayerLose() {
        initial.removeLast();
        initial.add(new Card(Rank.JACK, Suit.DIAMONDS, false));
        initial.add(new Card(Rank.QUEEN, Suit.DIAMONDS, false));
        initial.addFirst(new Card(Rank.KING, Suit.SPADES, false));
        Game game = new Game(new Deck(initial));

        game.nextRound();

        game.dealToPlayer();
        assertEquals(State.LOSE, game.getState());
    }

    @Test
    void testDealerBlackJack() {
        initial.add(new Card(Rank.QUEEN, Suit.CLUBS, false));
        Collections.reverse(initial);
        Game game = new Game(new Deck(initial));

        game.dealStartingCards();
        assertEquals(State.START, game.getState());

        while (game.dealerTurn().isPresent()) {}

        assertEquals(State.BLACKJACK_LOSE, game.getState());
        game.updateScore();
        assertEquals(0, game.getPlayerScore());
        assertEquals(1, game.getDealerScore());
    }

    @Test
    void testReguralDealerTurn() {
        initial.add(new Card(Rank.ACE, Suit.DIAMONDS, false));
        Collections.reverse(initial);
        Game game = new Game(new Deck(initial));

        game.dealStartingCards();
        game.dealerTurn();
        game.dealerTurn();

        assertTrue(game.getDealerPoints() < 21);
        assertEquals(State.WIN, game.getState());
    }
}
