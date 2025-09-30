package sys.pro;

import java.util.List;

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
  protected Integer evalInternal(List<Variable> vars, List<Number> nums) {
    return lhs.evalInternal(vars, nums) * rhs.evalInternal(vars, nums);
  }
}
