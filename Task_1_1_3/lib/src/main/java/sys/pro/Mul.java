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
}
