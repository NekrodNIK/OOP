package sys.pro;

import java.util.Map;

public abstract class BinaryOperator extends Expression {
    protected final Expression lhs;
    protected final Expression rhs;
    private final char symbol;

    protected abstract Integer calcOperator(Integer a, Integer b);

    public BinaryOperator(Expression lhs, Expression rhs, char symbol) {
        this.lhs = lhs;
        this.rhs = rhs;
        this.symbol = symbol;
    }

    protected Expression simplifyInternal(Expression slhs, Expression srhs) {
        if (slhs instanceof Number && srhs instanceof Number) {
            return new Number(calcOperator(((Number) slhs).getValue(), ((Number) srhs).getValue()));
        } else {
            if (this instanceof Add) {
                return new Add(slhs, srhs);
            } else if (this instanceof Sub) {
                return new Sub(slhs, srhs);
            } else if (this instanceof Mul) {
                return new Mul(slhs, srhs);
            } else if (this instanceof Div) {
                return new Div(slhs, srhs);
            } else {
                throw new IllegalCallerException();
            }
        }
    }

    @Override
    public Expression simplify() {
        Expression slhs = lhs.simplify();
        Expression srhs = rhs.simplify();

        return simplifyInternal(slhs, srhs);
    }

    @Override
    protected Integer evalInternal(Map<Variable, Number> map) {
        return calcOperator(lhs.evalInternal(map), rhs.evalInternal(map));
    }

    @Override
    public boolean equals(Object other) {
        return other != null
                && getClass() == other.getClass()
                && lhs.equals(((BinaryOperator) other).lhs)
                && rhs.equals(((BinaryOperator) other).rhs);
    }

    @Override
    public String toString() {
        return "(%s %c %s)".formatted(lhs, symbol, rhs);
    }
}
