package sys.pro;


import org.junit.jupiter.api.Test;

public class DerivativeTest {
  @Test
  void testExpr() {
    System.out.println(Parser.parse("x+x+y+z+1+2*x+2/x-x").derivative(new Variable("x")));
  }
}
