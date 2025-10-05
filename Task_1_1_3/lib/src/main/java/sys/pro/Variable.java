package sys.pro;

import java.util.Map;

/** 'Variable' expression. */
public class Variable extends Expression {
    String name;

    public Variable(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object other) {
        return other != null && other instanceof Variable && name.equals(((Variable) other).name);
    }

    @Override
    public Expression derivative(Variable var) {
        if (equals(var)) {
            return new Number(1);
        } else {
            return new Number(0);
        }
    }

    @Override
    protected Integer evalInternal(Map<Variable, Number> map) {
        for (Variable var : map.keySet()) {
            if (name.equals(var.name)) {
                return map.get(var).getValue();
            }
        }

        throw new IllegalArgumentException();
    }

    @Override
    public Expression simplify() {
        return this;
    }
}
