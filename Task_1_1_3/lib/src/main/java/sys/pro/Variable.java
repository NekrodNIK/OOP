package sys.pro;

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
}

