package sys.pro;

public abstract class BinaryOperator extends Expression {
  protected Expression lhs;
  protected Expression rhs;
  protected char symbol;

  public BinaryOperator(Expression lhs, Expression rhs) {
    this.lhs = lhs;
    this.rhs = rhs;
  }

  @Override
  public boolean equals(Object other) {
    return getClass() == other.getClass() &&
           lhs.equals(((BinaryOperator) other).lhs) &&
           rhs.equals(((BinaryOperator) other).rhs);
  }
  
  @Override
  public String toString() {
    return "(%s %c %s)".formatted(lhs, symbol, rhs);
  }
}
