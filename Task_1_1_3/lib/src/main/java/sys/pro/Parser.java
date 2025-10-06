package sys.pro;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

/** 'Parser' class. */
public class Parser {
    /**
     * Parse the expression from string.  
     *
     * @param string Some string
     * @return expression
     * @throws IllegalArgumentException if the string is not a valid string representation
     */
    public static Expression parse(String string) {
        try {
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
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid string representation");
        }
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
                    while (!operators.empty()
                            && !operators.peek().equals("(")
                            && operatorPrecedence(operators.peek().charAt(0))
                                    > operatorPrecedence(cur.charAt(0))) {
                        out.add(
                                genOperator(
                                        operators.pop().charAt(0),
                                        out.removeLast(),
                                        out.removeLast()));
                    }
                    operators.push(cur);
                    break;
                case "(":
                    operators.push(cur);
                    break;
                case ")":
                    while (!operators.empty() && !operators.peek().equals("(")) {
                        out.add(
                                genOperator(
                                        operators.pop().charAt(0),
                                        out.removeLast(),
                                        out.removeLast()));
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

        return out.get(0);
    }

    private static int operatorPrecedence(char c) {
        switch (c) {
            case '+':
                return 1;
            case '-':
                return 1;
            case '*':
                return 2;
            case '/':
                return 2;
            default:
                throw new IllegalArgumentException("Illegal operator");
        }
    }

    private static Expression genOperator(char c, Expression rhs, Expression lhs) {
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
                throw new IllegalArgumentException("Illegal operator");
        }
    }
}
