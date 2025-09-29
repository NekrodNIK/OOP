package sys.pro;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class Parser {
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

  private static Expression genOperator(char c, Expression lhs, Expression rhs) {
    switch (c) {
      case '+':
        return new Add(lhs, rhs);
      case '-':
        return new Sub(lhs, rhs);
      case '*':
        return new Mul(lhs, rhs);
      case '/':
        return new Div(lhs, rhs);
      default:
        return new Add(new Number(0), new Number(0));
    }
  }

  public static Expression parseInternal(Iterator<String> tokens) {
    Stack<String> operators = new Stack<String>();
    ArrayList<Expression> out = new ArrayList<Expression>();

    while (tokens.hasNext()) {
      String cur = tokens.next();
      switch (cur) {
        case "+":
        case "-":
        case "*":
        case "/":
          while (!operators.empty() && operators.peek() != "("
              && operatorPrecedence(operators.peek().charAt(0)) > operatorPrecedence(cur.charAt(0))) {
            out.add(genOperator(operators.pop().charAt(0), out.removeLast(), out.removeLast()));
          }
          operators.push(cur);
          break;
        case "(":
          operators.push(cur);
          break;
        case ")":
          while (!operators.empty() && operators.peek() != "(") {
            out.add(genOperator(operators.pop().charAt(0), out.removeLast(), out.removeLast()));
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
      out.add(genOperator(operators.pop().charAt(0), out.removeLast(), out.removeLast()));
    }

    return out[0];
  }
}
