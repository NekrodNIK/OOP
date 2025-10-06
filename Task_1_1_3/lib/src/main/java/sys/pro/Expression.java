package sys.pro;

import java.util.HashMap;
import java.util.Map;

/** 'Expression' abstract parent class. */
public abstract class Expression {
    /**
     * Print the expression.
     *
     * @return string
     */
    public String print() {
        return toString();
    }

    /**
     * Take derivative of the expression.
     *
     * @return new_expression
     */
    public abstract Expression derivative(Variable var);

    /**
     * Simplify the expression.
     *
     * @return new_expression
     */
    public abstract Expression simplify();

    protected abstract Integer evalInternal(Map<Variable, Number> map);

    /**
     * Evaluate the expression.
     *
     * @param def String definitions (like: int x = 1; int y = 0)
     * @return result
     * @throws IllegalArgumentException if the definition is invalid
     */
    public Integer eval(String def) {
        try {
            Map<Variable, Number> map = new HashMap<Variable, Number>();

            for (String line : def.split(";")) {
                String[] splitted = line.split("=");
                map.put(
                        new Variable(splitted[0].strip()),
                        new Number(Integer.parseInt(splitted[1].strip())));
            }

            return evalInternal(map);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid definition");
        }
    }
}
