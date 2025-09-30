package sys.pro;

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
  protected Integer calcOperator(Integer a, Integer b) {
    return a-b;
  }  
}
