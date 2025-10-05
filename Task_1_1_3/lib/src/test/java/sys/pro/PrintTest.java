package sys.pro;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/** PrintTest. */
public class PrintTest {
  @Test
  void testExpr() {
    String expected = "((((x + x) / x) + y) * 8)";
    Expression expr = new Mul(
        new Add(new Div(new Add(new Variable("x"), new Variable("x")), new Variable("x")), new Variable("y")),
        new Number(8));
    assertEquals(expected, expr.print());
  }
}
