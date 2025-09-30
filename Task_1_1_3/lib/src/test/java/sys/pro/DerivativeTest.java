package sys.pro;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DerivativeTest {
  @Test
  void testExpr() {
    assertEquals(Parser.parse("1+1+0+0+0+2-1+0"), Parser.parse("x+x+y+z+1+2*x-x+x/x").derivative(new Variable("x")));
  }
}
