package sys.pro;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;
import java.util.Stack;

public class Parser {
  public static Expression parse(String string) {
    ArrayList<String> tokens = new ArrayList<String>();
    
    for (int i = 0; i < string.length(); i++) {
      String token = new String();
      char c = string.charAt(i);

      if (Character.isSpaceChar(c)) {
        continue;
      }

      if (Character.isAlphabetic(c)) {
        while (i < string.length() && Character.isAlphabetic((c = string.charAt(i)))) {
          token += c;
          i++;
        }
        i--;
      } else if (Character.isDigit(c)) {
        while (i < string.length() && Character.isDigit((c = string.charAt(i)))) {
          token += c;
          i++;
        }
        i--;
      } else {
        token += c;
      }

      tokens.add(token.trim());
    }

    return parseInternal(tokens.iterator());
  }
  
  private static Expression parseInternal(Iterator<String> tokens) {
    Stack<String> operators = new Stack<String>();
    ArrayList<Expression> out = new ArrayList<Expression>();

    while (tokens.hasNext()) {
      String cur = tokens.next();
      switch (cur) {
        case "+":
        case "-":
        case "*":
        case "/":
          while (!operators.empty() && !operators.peek().equals("(")
              && operatorPrecedence(operators.peek().charAt(0)) > operatorPrecedence(cur.charAt(0))) {
            out.add(genOperator(operators.pop().charAt(0), out.removeLast(), out.removeLast()).get());
          }
          operators.push(cur);
          break;
        case "(":
          operators.push(cur);
          break;
        case ")":
          while (!operators.empty() && !operators.peek().equals("(")) {
            out.add(genOperator(operators.pop().charAt(0), out.removeLast(), out.removeLast()).get());
          }
          if (!operators.empty()) {
            operators.pop();
          }
          break;
        default:
          if (Character.isDigit(cur.charAt(0))) {
            out.add(new Number(Integer.parseInt(cur)));
          } else {
            out.add(new Variable(cur));
          }
      }
    }

    while (!operators.empty()) {
      out.add(genOperator(operators.pop().charAt(0), out.removeLast(), out.removeLast()).get());
    }

    return out.get(0);
  }
  
  private static int operatorPrecedence(char c) {
    switch (c) {
      case '+':
        return 0;
      case '-':
        return 0;
      case '*':
        return 1;
      case '/':
        return 1;
    }

    return 0;
  }

  private static Optional<Expression> genOperator(char c, Expression rhs, Expression lhs) {
    switch (c) {
      case '+':
        return Optional.of(new Add(lhs, rhs));
      case '-':
        return Optional.of(new Sub(lhs, rhs));
      case '*':
        return Optional.of(new Mul(lhs, rhs));
      case '/':
        return Optional.of(new Div(lhs, rhs));
    }

    return Optional.empty();
  }
}
