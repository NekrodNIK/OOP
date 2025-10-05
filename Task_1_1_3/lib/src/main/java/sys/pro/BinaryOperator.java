package sys.pro;

import java.util.Map;

public abstract class BinaryOperator extends Expression {
    final protected Expression lhs;
    final protected Expression rhs;
    final private char symbol;

    protected abstract Integer calcOperator(Integer a, Integer b);

    public BinaryOperator(Expression lhs, Expression rhs, char symbol) {
        this.lhs = lhs;
        this.rhs = rhs;
        this.symbol = symbol;
    }

    protected Expression simplifyInternal(Expression sLhs, Expression sRhs) {
        if (sLhs instanceof Number && sRhs instanceof Number) {
            return new Number(calcOperator(((Number) sLhs).getValue(), ((Number) sRhs).getValue()));
        } else {
            if (this instanceof Add) {
                return new Add(sLhs, sRhs);
            } else if (this instanceof Sub) {
                return new Sub(sLhs, sRhs);
            } else if (this instanceof Mul) {
                return new Mul(sLhs, sRhs);
            } else if (this instanceof Div) {
                return new Div(sLhs, sRhs);
            } else {
                throw new IllegalCallerException();
            }
        }
    }

    @Override
    public Expression simplify() {
        Expression sLhs = lhs.simplify();        
        Expression sRhs = rhs.simplify();        

        return simplifyInternal(sLhs, sRhs);
    }

    @Override
    protected Integer evalInternal(Map<Variable, Number> map){
        return calcOperator(lhs.evalInternal(map), rhs.evalInternal(map));
    }

    @Override
    public boolean equals(Object other) {
        return getClass() == other.getClass()
                && lhs.equals(((BinaryOperator) other).lhs)
                && rhs.equals(((BinaryOperator) other).rhs);
    }

    @Override
    public String toString() {
        return "(%s %c %s)".formatted(lhs, symbol, rhs);
    }
}
