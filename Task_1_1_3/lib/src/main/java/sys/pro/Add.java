package sys.pro;

import java.util.List;

public class Add extends BinaryOperator {
  public Add(Expression lhs, Expression rhs) {
    super(lhs, rhs);
    symbol = '+';
  }
  
  @Override
  public Expression derivative(Variable var) {
    return new Add(lhs.derivative(var), rhs.derivative(var));
  }

  @Override
  protected Integer evalInternal(List<Variable> vars, List<Number> nums) {
    return lhs.evalInternal(vars, nums) + rhs.evalInternal(vars, nums);
  }
}
