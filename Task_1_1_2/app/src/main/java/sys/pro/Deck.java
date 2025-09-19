package sys.pro;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Deck {
  private ArrayList<Card> arr = new ArrayList<Card>();

  public void addAll(Collection<Card> inital) {
    arr.addAll(inital);
  }

  public void shuffle() {
    Collections.shuffle(arr);
  }
  
  public void clear() {
    arr.clear();
  }

  public void dealFaceUp(Hand hand) {
    Card card = arr.removeLast();
    card.show();
    hand.add(card);
  }
  
  public void dealFaceDown(Hand hand) {
    Card card = arr.removeLast();
    card.hide();
    hand.add(card);
  }

  public List<Card> toList() {
    return arr;
  }

  public int size() {
    return arr.size();
  }
}
