package sys.pro;

public class Number extends Expression {
  private Integer value;
  
  public Number(int value) {
    this.value = value;
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
}
