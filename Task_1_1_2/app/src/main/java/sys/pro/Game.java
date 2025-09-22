package sys.pro;

import java.util.Optional;

/** Game driver. */
public class Game {
  private Deck deck;
  private Hand player;
  private Hand dealer;

  private int pscore;
  private int dscore;
  private State state;

  /** Default constructor. */
  public Game() {
    this(new Deck());
  }

  /**
   * Constructor.
   *
   * @param deck Initial deck
   */
  public Game(Deck deck) {
    this.deck = deck;
    player = new Hand();
    dealer = new Hand();
    pscore = 0;
    dscore = 0;
    state = State.START;
  }

  /** Deal cards at the beginning of the game. */
  public void dealStartingCards() {
    deck.dealFaceUp(player);
    deck.dealFaceUp(player);

    if (player.getTotal() == 21) {
      state = State.BLACKJACK_WIN;
    }

    deck.dealFaceDown(dealer);
    deck.dealFaceUp(dealer);
  }

  /**
   * Deal a card to a player.
   *
   * @return Card
   */
  public Card dealToPlayer() {
    deck.dealFaceUp(player);

    if (player.getTotal() > 21) {
      state = State.LOSE;
    }

    return player.getLastAddedCard();
  }

  /**
   * Make one dealer turn. If the dealer's move is not possible, returns None.
   *
   * @return Card or None
   */
  public Optional<Card> dealerTurn() {
    if (state != State.START) {
      return Optional.empty();
    }

    Optional<Card> card = dealer.findHiddenCard();
    if (dealer.getTotal() < 17 && card.isPresent()) {
      card.get().show();
      selectState();
      return card;
    }

    selectState();
    return Optional.empty();
  }

  private void selectState() {
    if (dealer.getTotal() == 21 && player.getTotal() == 21) {
      state = State.BLACKJACK_DRAW;
    } else if (dealer.getTotal() == 21) {
      state = State.BLACKJACK_LOSE;
    } else if (dealer.getTotal() > 21) {
      state = State.WIN;
    } else if (dealer.getTotal() == player.getTotal()) {
      state = State.DRAW;
    } else if (dealer.getTotal() > player.getTotal()) {
      state = State.LOSE;
    } else {
      state = State.WIN;
    }
  }

  /** Update score. */
  public void updateScore() {
    switch (state) {
      case WIN:
      case BLACKJACK_WIN:
        pscore++;
        break;
      case LOSE:
      case BLACKJACK_LOSE:
        dscore++;
        break;
      default:
    }
  }

  /** Start the next round. */
  public void nextRound() {
    deck.restore();
    deck.shuffle();
    player.clear();
    dealer.clear();
    state = State.START;

    dealStartingCards();
  }

  /**
   * Get game state.
   *
   * @return state
   */
  public State getState() {
    return state;
  }

  /**
   * Returns player score.
   * 
   * @return score
   */
  public int getPlayerScore() {
    return pscore;
  }

  /**
   * Returns dealer score.
   * 
   * @return score
   */
  public int getDealerScore() {
    return dscore;
  }

  /**
   * Returns player hand.
   * 
   * @return hand
   */
  public Hand getPlayerHand() {
    return new Hand(player);
  }

  /**
   * Returns dealer hand.
   * 
   * @return hand
   */
  public Hand getDealerHand() {
    return new Hand(dealer);
  }

  /**
   * Returns player points.
   * 
   * @return points
   */
  public int getPlayerPoints() {
    return player.getTotal();
  }

  /**
   * Returns dealer points.
   * 
   * @return points
   */
  public int getDealerPoints() {
    return dealer.getTotal();
  }
}
