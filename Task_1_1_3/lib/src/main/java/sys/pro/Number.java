package sys.pro;

import java.util.Map;

/** 'Number' expression. */
public class Number extends Expression {
    private Integer value;

    public Number(int value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other != null && other instanceof Number && value.equals(((Number) other).value);
    }

    @Override
    public Expression derivative(Variable var) {
        return new Number(0);
    }

    @Override
    protected Integer evalInternal(Map<Variable, Number> map) {
        return value;
    }

    @Override
    public Expression simplify() {
        return this;
    }
}
