package sys.pro;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/** EvalTest. */
public class EvalTest {
  @Test
  void testExpr() {
    Expression expr = new Sub(
        new Mul(new Add(new Div(new Add(new Variable("x"), new Variable("x")), new Variable("x")), new Variable("y")),
            new Number(8)),
        new Variable("y"));
    assertEquals(121, expr.eval("x = 10; y = 15;"));
  }
}
