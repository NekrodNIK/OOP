package sys.pro;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DeckTest {
  Deck deck;
  List<Card> initial;
  Hand hand;

  @BeforeEach
  void setUp() {
    initial = Stream.of(Rank.values())
        .flatMap(rank -> Stream.of(Suit.values())
            .map(suit -> new Card(rank, suit, true)))
        .toList();

    deck = new Deck(initial);
    hand = new Hand();
  }

  @Test
  void testDealFaceUp() {
    deck.dealFaceUp(hand);
    assertEquals(initial.getLast(), hand.getLastAddedCard());
    assertEquals(false, hand.getLastAddedCard().isHidden());
  }

  @Test
  void testDealFaceDown() {
    deck.dealFaceDown(hand);
    assertEquals(initial.getLast(), hand.getLastAddedCard());
    assertEquals(true, hand.getLastAddedCard().isHidden());
  }

  @Test
  void testShuffle() {
    assertEquals(initial, deck.toList());
    deck.shuffle();
    assertNotEquals(initial, deck.toList());
  }

  @Test
  void testClear() {
    deck.clear();
    assertEquals(0, deck.size());
  }
}
