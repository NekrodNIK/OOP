package sys.pro;

import java.util.Optional;
import java.util.Scanner;

/** A class containing the entry point. */
public class App {
    /**
     * The entry point. Run the game.
     *
     * @param args CLI arguments
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Game game = new Game();
        println("Добро пожаловать в Блэкджек!");

        int r = -1;
        while (true) {
            r++;
            println("Раунд %d".formatted(r));
            println("Дилер раздал карты");
            nextRound(game, input);
            game.updateScore();
            printScore(game);
        }
    }

    /**
     * Run next round.
     *
     * @param game Some game
     * @param input Scanner object
     */
    protected static State nextRound(Game game, Scanner input) {
        game.nextRound();

        printStatus(game);

        if (game.getState() == State.BLACKJACK_WIN) {
            return game.getState();
        }

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
                        println("Вы открыли карту %s".formatted(game.dealToPlayer()));
                        printStatus(game);
                        if (game.getState() != State.START) {
                            return game.getState();
                        }

                        break inner;
                    default:
                        println("Некорректный ввод");
                }
            }
        }

        println("\nХод дилера:\n-------");

        Optional<Card> card;
        while ((card = game.dealerTurn()).isPresent()) {
            println("Дилер открывает карту %s".formatted(card.get()));
            printStatus(game);
            break;
        }

        return game.getState();
    }

    private static void printStatus(Game game) {
        println("    Ваши карты: %s".formatted(game.getPlayerHand()));
        println("    Карты дилера: %s\n".formatted(game.getDealerHand()));
    }

    private static void printScore(Game game) {
        switch (game.getState()) {
            case BLACKJACK_DRAW:
                print("Блэкждэк! ");
            case DRAW:
                print("В этом раунде ничья. ");
                break;
            case BLACKJACK_LOSE:
                print("Блэкждэк! ");
            case LOSE:
                print("Вы проиграли раунд. ");
                break;
            case BLACKJACK_WIN:
                print("Блэкждэк! ");
            case WIN:
                print("Вы выиграли раунд! ");
                break;
            default:
        }

        int pscore = game.getPlayerScore();
        int dscore = game.getDealerScore();

        print("Счёт %d:%d в ".formatted(pscore, dscore));

        if (pscore > dscore) {
            print("вашу пользу");
        } else if (pscore < dscore) {
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
