package sys.pro;

import java.util.Optional;

public class Game {
  private Deck deck;
  private Hand player;
  private Hand dealer;

  private int pscore;
  private int dscore;
  private Boolean blackjack;

  public Game() {
    this(new Deck());
  }

  public Game(Deck deck) {
    this.deck = deck;
    player = new Hand();
    dealer = new Hand();
    pscore = 0;
    dscore = 0;
    blackjack = false;
  }

  public void dealStartingCards() {
    deck.dealFaceUp(player);
    deck.dealFaceUp(player);

    if (player.getTotal() == 21) {
      blackjack = true;
    }

    deck.dealFaceDown(dealer);
    deck.dealFaceUp(dealer);
  }

  public Card dealToPlayer() {
    deck.dealFaceUp(player);
    return player.getLastAddedCard();
  }

  public Optional<String> dealerTurn() {
    Optional<Card> mes;
    if (dealer.getTotal() >= 17 || (mes = dealer.findHiddenCard()).isEmpty()) {
      return Optional.empty();
    }

    mes.get().show();
    if (dealer.getTotal() == 21) {
      blackjack = true;
    }

    return Optional.of(mes.get().toString());
  }

  public Boolean isEnd() {
    return blackjack || player.getTotal() > 21 || dealer.getTotal() > 21;
  }

  public void updateScore() {
    if (isVictory()) {
      pscore++;
    } else {
      dscore++;
    }
  }

  public void nextRound() {
    deck.restore();
    deck.shuffle();
    player.clear();
    dealer.clear();
    blackjack = false;
    
    dealStartingCards();
  }

  public Boolean isVictory() {
    if (blackjack && player.getTotal() == 21) {
      return true;
    }

    if (blackjack && dealer.getTotal() == 21) {
      return false;
    }

    if (player.getTotal() < 21 && dealer.getTotal() < 21) {
      return player.getTotal() > dealer.getTotal();
    }

    return dealer.getTotal() > 21;
  }

  public Boolean isBlackJack() {
    return blackjack;
  }

  public int getPlayerScore() {
    return pscore;
  }

  public int getDealerScore() {
    return dscore;
  }

  public Hand getPlayerHand() {
    return new Hand(player);
  }

  public Hand getDealerHand() {
    return new Hand(dealer);
  }

  public int getPlayerPoints() {
    return player.getTotal();
  }

  public int getDealerPoints() {
    return dealer.getTotal();
  }
}
