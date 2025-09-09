package sys.pro;

import java.util.Optional;
import java.util.Scanner;

public class App {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    Game game = new Game();
    println("Добро пожаловать в Блэкджек!");

    int r = -1;
    round: while (true) {
      game.nextRound();
      r++;
      println("Раунд %d".formatted(r));

      game.dealCards();
      println("Дилер раздал карты");

      printStatus(game);

      if (game.isBlackJack()) {
        updateAndPrintScore(game);
        continue;
      };

      println("Ваш ход:\n-------");
      outer: while (true) {
        println("Введите “1”, чтобы взять карту, и “0”, чтобы остановиться...");

        inner: while (true) {
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
    println(
        "    Ваши карты: %s => %d"
            .formatted(game.getPlayerHand(), game.getPlayerPoints()));
    println(
        "    Карты дилера: %s => %d\n"
            .formatted(game.getDealerHand(), game.getDealerPoints()));
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
