package sys.pro;

import java.util.List;

public class Sub extends BinaryOperator {
  public Sub(Expression lhs, Expression rhs) {
    super(lhs, rhs);
    symbol = '-';
  }
  
  @Override
  public Expression derivative(Variable var) {
    return new Sub(lhs.derivative(var), rhs.derivative(var));
  }

  @Override
  protected Integer evalInternal(List<Variable> vars, List<Number> nums) {
    return lhs.evalInternal(vars, nums) - rhs.evalInternal(vars, nums);
  }
}
