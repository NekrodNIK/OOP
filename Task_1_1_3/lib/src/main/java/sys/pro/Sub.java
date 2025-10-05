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
    public Expression simplify() {
        Expression sLhs = lhs.simplify();
        Expression sRhs = rhs.simplify();

        if (sLhs.equals(sRhs)) {
            return new Number(0);
        }

        return this.simplifyInternal(sLhs, sRhs);
    }
}
