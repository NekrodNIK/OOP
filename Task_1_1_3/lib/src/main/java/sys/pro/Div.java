package sys.pro;

import java.util.List;

public class Div extends BinaryOperator {
  public Div(Expression lhs, Expression rhs) {
    super(lhs, rhs);
    symbol = '/';
  }

  @Override
  public Expression derivative(Variable var) {
    return new Div(
        new Sub(
            new Mul(lhs.derivative(var), rhs), new Mul(lhs, rhs.derivative(var))),
        new Mul(rhs, rhs));
  }

  @Override
  protected Integer calcOperator(Integer a, Integer b) {
    return a / b;
  }
}
