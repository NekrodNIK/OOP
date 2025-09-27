package sys.pro;

abstract class Expression {
  
  
  public static Expression parse(String string) {
    return new Expression(string);
  }
}
