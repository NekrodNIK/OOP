package sys.pro;

public class Mul extends BinaryOperator {
  public Mul(Expression lhs, Expression rhs) {
    super(lhs, rhs);
    symbol = '*';
  }

  @Override
  public Expression derivative(Variable var) {
    return new Add(new Mul(lhs.derivative(var), rhs), new Mul(lhs, rhs.derivative(var)));
  }

  @Override
  protected Integer calcOperator(Integer a, Integer b) {
    return a * b;
  }

  @Override
  public Expression simplify() {
    Expression some = super.simplify();
    if (some instanceof Number) {
      return some;
    }

    Expression sLhs = lhs.simplify();
    Expression sRhs = rhs.simplify();

    if (sLhs instanceof Number || sRhs instanceof Number) {
      Number sLhsNum = (Number) sLhs;
      Number sRhsNum = (Number) sRhs;
      if (sLhsNum.getValue() == 0 || sRhsNum.getValue() == 0) {
        return new Number(0);
      }
      if (sLhsNum.getValue() == 1) {
        return sRhsNum;
      }
      if (sRhsNum.getValue() == 1) {
        return sLhsNum;
      }
    }

    return this;
  }
}
