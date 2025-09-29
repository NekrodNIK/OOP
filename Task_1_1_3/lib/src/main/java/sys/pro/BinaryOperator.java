package sys.pro;

public abstract class BinaryOperator extends Expression {
  protected int precedence;

  public int getPrecedence() {
    return precedence;
  }
}
