package sys.pro;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SimplifyTest {
  @Test
  void testExpr() {
    String input = "(10+100)*x-11-22*3";
    Expression expected = new Sub(new Mul(new Number(111), new Variable("x")), new Number(77));
    assertEquals(expected, Parser.parse(input).simplify());
  }
}
