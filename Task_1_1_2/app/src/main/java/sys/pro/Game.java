package sys.pro;

import java.util.Optional;

/** Game driver. */
public class Game {
    private Deck deck;
    private Hand player;
    private Hand dealer;

    private int pscore;
    private int dscore;
    private Boolean blackjack;

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
        blackjack = false;
    }

    /** Deal cards at the beginning of the game. */
    public void dealStartingCards() {
        deck.dealFaceUp(player);
        deck.dealFaceUp(player);

        if (player.getTotal() == 21) {
            blackjack = true;
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
        return player.getLastAddedCard();
    }

    /**
     * Make one dealer turn. If the dealer's move is not possible, returns None.
     *
     * @return Card or None
     */
    public Optional<Card> dealerTurn() {
        Optional<Card> mes;
        if (dealer.getTotal() >= 17 || (mes = dealer.findHiddenCard()).isEmpty()) {
            return Optional.empty();
        }

        mes.get().show();
        if (dealer.getTotal() == 21) {
            blackjack = true;
        }

        return Optional.of(mes.get());
    }

    /** Update score. */
    public void updateScore() {
        if (isVictory()) {
            pscore++;
        } else {
            dscore++;
        }
    }

    /** Start the next round. */
    public void nextRound() {
        deck.restore();
        deck.shuffle();
        player.clear();
        dealer.clear();
        blackjack = false;

        dealStartingCards();
    }

    /**
     * Check if the round is end.
     *
     * @return status
     */
    public Boolean isEnd() {
        return blackjack || player.getTotal() > 21 || dealer.getTotal() > 21;
    }

    /**
     * check if the round is player victory.
     *
     * @return status
     */
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

    /**
     * check if the round is blackjack.
     *
     * @return status
     */
    public Boolean isBlackJack() {
        return blackjack;
    }

    /** Returns player score. */
    public int getPlayerScore() {
        return pscore;
    }

    /** Returns dealer score. */
    public int getDealerScore() {
        return dscore;
    }

    /** Returns player hand. */
    public Hand getPlayerHand() {
        return new Hand(player);
    }

    /** Returns dealer hand. */
    public Hand getDealerHand() {
        return new Hand(dealer);
    }

    /** Returns player points. */
    public int getPlayerPoints() {
        return player.getTotal();
    }

    /** Returns dealer points. */
    public int getDealerPoints() {
        return dealer.getTotal();
    }
}
