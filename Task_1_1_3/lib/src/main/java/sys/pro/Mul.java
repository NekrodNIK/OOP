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

    @Override
    protected Integer calcOperator(Integer a, Integer b) {
        return a * b;
    }

    @Override
    public Expression simplify() {
        Expression some = super.simplify();
        if (some instanceof Number) {
            return some;
        }

        Expression sLhs = lhs.simplify();
        Expression sRhs = rhs.simplify();

        if (sLhs instanceof Number && ((Number) sLhs).getValue() == 0) {
            return new Number(0);
        }
        
        if (sRhs instanceof Number && ((Number) sRhs).getValue() == 0) {
            return new Number(0);
        }
        
        if (sLhs instanceof Number && ((Number) sLhs).getValue() == 1) {
            return rhs;
        }
        
        if (sRhs instanceof Number && ((Number) sRhs).getValue() == 1) {
            return lhs;
        }

        return this;
    }
}
