package sys.pro;

import java.util.List;

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
        return other instanceof Variable && name.equals(((Variable) other).name);
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
    protected Integer evalInternal(List<Variable> vars, List<Number> nums) {
        for (int i = 0; i < vars.size(); i++) {
            if (name.equals(vars.get(i).name)) {
                return nums.get(i).getValue();
            }
        }

        return 0;
    }

    @Override
    public Expression simplify() {
        return this;
    }
}
