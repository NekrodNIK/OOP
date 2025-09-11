package sys.pro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Game game = new Game();
        println("Добро пожаловать в Блэкджек!");

        int r = -1;
        round:
        while (true) {
            game.nextRound();
            r++;
            println("Раунд %d".formatted(r));

            game.dealCards();
            println("Дилер раздал карты");

            printStatus(game);

            if (game.isBlackJack()) {
                updateAndPrintScore(game);
                continue;
            }
            ;

            println("Ваш ход:\n-------");
            outer:
            while (true) {
                println("Введите “1”, чтобы взять карту, и “0”, чтобы остановиться...");

                inner:
                while (true) {
                    switch (input.nextLine().strip()) {
                        case "0":
                            break outer;
                        case "1":
                            println("Вы открыли карту %s".formatted(game.takeCard()));
                            printStatus(game);
                            if (game.isEnd()) {
                                updateAndPrintScore(game);
                                continue round;
                            }

                            break inner;
                        default:
                            println("Некорректный ввод");
                    }
                }
            }

            println("\nХод дилера:\n-------");

            Optional<String> str;
            while ((str = game.dealerTurn()).isPresent()) {
                println("Дилер открывает карту %s".formatted(str.get()));
                printStatus(game);
                break;
            }

            updateAndPrintScore(game);
        }

        // input.close();
    }

    private static void printStatus(Game game) {
        println("    Ваши карты: %s".formatted(game.getPlayerHand()));
        println("    Карты дилера: %s\n".formatted(game.getDealerHand()));
    }

    private static void updateAndPrintScore(Game game) {
        game.updateScore();
        print(game.isBlackJack() ? "Блэкждэк! " : "");

        print(game.isVictory() ? "Вы выиграли раунд!" : "Вы проиграли раунд.");
        print(" ");

        int p_score = game.getPlayerScore();
        int d_score = game.getDealerScore();

        print("Счёт %d:%d в ".formatted(p_score, d_score));

        if (p_score > d_score) {
            print("вашу пользу");
        } else if (p_score < d_score) {
            print("пользу дилера");
        } else {
            print("ничью");
        }
        print(".\n\n");
    }

    private static void println(String str) {
        System.out.println(str);
    }

    private static void print(String str) {
        System.out.print(str);
    }
}

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

        if (player.getTotal() == 21) blackjack = true;

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
        if (dealer.getTotal() == 21) blackjack = true;

        return Optional.of(mes.get().toString());
    }

    public Boolean isEnd() {
        return player.getTotal() > 21 || dealer.getTotal() > 21;
    }

    public void updateScore() {
        if (isVictory()) p_score++;
        else d_score++;
    }

    public void nextRound() {
        deck.clear();
        player.clear();
        dealer.clear();

        init();
    }

    public Boolean isVictory() {
        if (blackjack && player.getTotal() == 21) return true;
        if (blackjack && dealer.getTotal() == 21) return false;

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
