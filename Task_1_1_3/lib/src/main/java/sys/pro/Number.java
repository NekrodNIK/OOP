package sys.pro;

import java.util.List;

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
        return true;
    }

    @Override
    public Expression derivative(Variable var) {
        return new Number(0);
    }

    @Override
    protected Integer evalInternal(List<Variable> vars, List<Number> nums) {
        return value;
    }

    @Override
    public Expression simplify() {
        return this;
    }
}
