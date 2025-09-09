package sys.pro;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collectors;

public class Hand {
  private HashSet<Card> cards;
  private Boolean threshold = false;

  public Hand(Collection<Card> init_cards) {
    cards = new HashSet<Card>(init_cards);
  }

  public Hand() {
    cards = new HashSet<Card>();
  }

  public void add(Card card) {
    cards.add(card);
  }

  public void clear() {
    threshold = false;
    cards.clear();
  }

  public int getTotal() {
    int total = cards.stream()
        .mapToInt((card) -> card.getPoints(false))
        .sum();

    threshold = total >= 21;
    if (threshold)
      total = cards.stream()
          .mapToInt((card) -> card.getPoints(threshold))
          .sum();
    return total;
  }

  public Optional<Card> findHiddenCard() {
    return cards.stream().filter((c) -> c.hidden).findAny();
  }

  private String cardToString(Card card) {
    if (card.hidden)
      return card.toString();
    return "%s (%d)".formatted(card, card.getPoints(threshold));
  }

  @Override
  public String toString() {
    return "[%s]".formatted(
        cards.stream()
            .map((card) -> cardToString(card))
            .collect(Collectors.joining(", ")));
  }
}
