package sys.pro;

import java.util.List;

public abstract class BinaryOperator extends Expression {
  protected Expression lhs;
  protected Expression rhs;
  protected char symbol;
  protected abstract Integer calcOperator(Integer a, Integer b);

  public BinaryOperator(Expression lhs, Expression rhs) {
    this.lhs = lhs;
    this.rhs = rhs;
  }

  @Override
  public Expression simplify() {
    Expression sLhs = lhs.simplify();
    Expression sRhs = rhs.simplify();
    
    if (sLhs instanceof Number && sRhs instanceof Number) {
      return new Number(calcOperator(((Number) sLhs).getValue(), ((Number) sRhs).getValue()));
    } else {
      return this;
    }
  }

  @Override
  protected Integer evalInternal(List<Variable> vars, List<Number> nums) {
    return calcOperator(lhs.evalInternal(vars, nums), rhs.evalInternal(vars, nums));
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
