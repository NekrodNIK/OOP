package sys.pro;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class Game {
  private Deck deck = new Deck();
  private Hand player = new Hand();
  private Hand dealer = new Hand();

  private int pscore = 0;
  private int dscore = 0;
  private Boolean blackjack;

  private void startRound() {
    List<Card> initial = Stream.of(Rank.values())
        .flatMap(rank -> Stream.of(Suit.values())
            .map(suit -> new Card(rank, suit, true)))
        .toList();

    deck.addAll(initial);
    deck.shuffle();

    deck.dealFaceUp(player);
    deck.dealFaceUp(player);

    if (player.getTotal() == 21) {
      blackjack = true;
    }

    deck.dealFaceDown(dealer);
    deck.dealFaceUp(dealer);
  }

  public String dealToPlayer() {
    deck.dealFaceUp(player);
    return player.getLastAddedCard().toString();
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
    return player.getTotal() > 21 || dealer.getTotal() > 21;
  }

  public void updateScore() {
    if (isVictory()) {
      pscore++;
    } else {
      dscore++;
    }
  }

  public void nextRound() {
    deck.clear();
    player.clear();
    dealer.clear();
    blackjack = false;
    
    startRound();
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

  public String getPlayerHand() {
    return player.toString();
  }

  public String getDealerHand() {
    return dealer.toString();
  }

  public int getPlayerPoints() {
    return player.getTotal();
  }

  public int getDealerPoints() {
    return dealer.getTotal();
  }
}
