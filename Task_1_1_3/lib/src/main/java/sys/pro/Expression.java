package sys.pro;

import java.util.ArrayList;
import java.util.List;

/** 'Expression' abstract parent class. */
public abstract class Expression {
    public String print() {
        return toString();
    }

    public abstract Expression derivative(Variable var);

    public abstract Expression simplify();

    protected abstract Integer evalInternal(List<Variable> vars, List<Number> nums);

    /** Evaluate expression.
     * @param definition String definitions (like: int x = 1; int y = 0)
     * @return result
     */
    public Integer eval(String def) {
        ArrayList<Variable> vars = new ArrayList<Variable>();
        ArrayList<Number> nums = new ArrayList<Number>();

        for (String line : def.split(";")) {
            String[] splitted = line.split("=");
            vars.add(new Variable(splitted[0].strip()));
            nums.add(new Number(Integer.parseInt(splitted[1].strip())));
        }

        return evalInternal(vars, nums);
    }
    ;
}
