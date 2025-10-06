package sys.pro;

/** 'Sub' binary operator. */
public class Sub extends BinaryOperator {
    public Sub(Expression lhs, Expression rhs) {
        super(lhs, rhs, '-');
    }

    @Override
    public Expression derivative(Variable var) {
        return new Sub(lhs.derivative(var), rhs.derivative(var));
    }

    @Override
    protected Integer calcOperator(Integer a, Integer b) {
        return a - b;
    }

    @Override
    protected Expression simplifyInternal(Expression slhs, Expression srhs) {
        if (slhs.equals(srhs)) {
            return new Number(0);
        }

        return new Sub(slhs, srhs);
    }
}
