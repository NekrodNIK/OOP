package sys.pro;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/** PrintTest. */
public class PrintTest {
  @Test
  void testAdd() {
    String expected = "(x + y)";
    Expression expr = new Add(new Variable("x"), new Variable("y"));
    assertEquals(expected, expr.print());
  }
  
  @Test
  void testSub() {
    String expected = "(x - y)";
    Expression expr = new Sub(new Variable("x"), new Variable("y"));
    assertEquals(expected, expr.print());
  }
  
  @Test
  void testMul() {
    String expected = "(x * y)";
    Expression expr = new Mul(new Variable("x"), new Variable("y"));
    assertEquals(expected, expr.print());
  }

  @Test
  void testDiv() {
    String expected = "(x / y)";
    Expression expr = new Div(new Variable("x"), new Variable("y"));
    assertEquals(expected, expr.print());
  }
  
  @Test
  void testExpr() {
    String expected = "((((x + x) / x) + y) * 8)";
    Expression expr = new Mul(
        new Add(new Div(new Add(new Variable("x"), new Variable("x")), new Variable("x")), new Variable("y")),
        new Number(8));
    assertEquals(expected, expr.print());
  }
}
