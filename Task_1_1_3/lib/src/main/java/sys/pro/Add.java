package sys.pro;

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
    protected Integer calcOperator(Integer a, Integer b) {
        return a + b;
    }
}
