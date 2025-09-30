package sys.pro;

public abstract class Expression extends Object {
  public String print() {
    return toString();
  }

  public abstract Expression derivative(Variable var);
}
