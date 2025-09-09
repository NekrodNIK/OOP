package sys.pro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

class Game {
  private ArrayList<Card> deck = new ArrayList<Card>();
  private Hand player = new Hand();
  private Hand dealer = new Hand();

  private int p_score = 0;
  private int d_score = 0;
  private Boolean blackjack;

  private void init() {
    blackjack = false;
    for (Card.Rank rank : Card.Rank.values()) {
      for (Card.Suit suit : Card.Suit.values()) {
        deck.add(new Card(rank, suit, false));
      }
    }

    Collections.shuffle(deck);
  }
  
  public void dealCards() {
    player.add(deck.removeLast());
    player.add(deck.removeLast());

    if (player.getTotal() == 21)
      blackjack = true;

    Card card = deck.removeLast();
    card.hidden = true;

    dealer.add(card);
    dealer.add(deck.removeLast());
  }

  public String takeCard() {
    Card card = deck.removeLast();
    player.add(card);    
    return card.toString();
  }

  public Optional<String> dealerTurn() {
    Optional<Card> mes;
    if (dealer.getTotal() >= 17 || (mes = dealer.findHiddenCard()).isEmpty())
      return Optional.empty();

    mes.get().hidden = false;
    if (dealer.getTotal() == 21)
      blackjack = true;
    
    return Optional.of(mes.get().toString());
  }

  public Boolean isEnd() {
    return player.getTotal() > 21 || dealer.getTotal() > 21;
  }

  public void updateScore() {
    if (isVictory())
      p_score++;
    else
      d_score++;
  }

  public void nextRound() {
    deck.clear();
    player.clear();
    dealer.clear();
    
    init();
  }
  
  public Boolean isVictory() {
    if (blackjack && player.getTotal() == 21)
      return true;
    if (blackjack && dealer.getTotal() == 21)
      return false;
    
    if (player.getTotal() < 21 && dealer.getTotal() < 21) 
      return player.getTotal() > dealer.getTotal();

    return dealer.getTotal() > 21;
  }

  public Boolean isBlackJack() {
    return blackjack;
  }

  public int getPlayerScore() {
    return p_score;
  }
  
  public int getDealerScore() {
    return d_score;
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
