package sys.pro;

/** 'Mul' binary operator. */
public class Mul extends BinaryOperator {
    public Mul(Expression lhs, Expression rhs) {
        super(lhs, rhs, '*');
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
        Expression slhs = lhs.simplify();
        Expression srhs = rhs.simplify();

        if (slhs instanceof Number && ((Number) slhs).getValue() == 0) {
            return new Number(0);
        }

        if (srhs instanceof Number && ((Number) srhs).getValue() == 0) {
            return new Number(0);
        }

        if (slhs instanceof Number && ((Number) slhs).getValue() == 1) {
            return srhs;
        }

        if (srhs instanceof Number && ((Number) srhs).getValue() == 1) {
            return slhs;
        }

        return this.simplifyInternal(slhs, srhs);
    }
}
