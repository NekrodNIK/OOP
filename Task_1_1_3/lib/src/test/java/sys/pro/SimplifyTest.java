package sys.pro;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/** SimplifyTest. */
public class SimplifyTest {
  @Test
  void testExpr() {
    Expression expr = new Add(new Mul(new Add(new Number(10), new Number(100)), new Variable("x")),
        new Add(new Number(11), new Mul(new Number(22), new Number(3))));
    Expression expected = new Add(new Mul(new Number(110), new Variable("x")), new Number(77));
    assertEquals(expected, expr.simplify());
  }

  @Test
  void testMulZero() {
    Expression expr = new Mul(new Variable("x"), new Number(0));
    Expression expected = new Number(0);
    assertEquals(expected, expr.simplify());
    expr = new Mul(new Number(0), new Variable("x"));
    assertEquals(expected, expr.simplify());
  }

  @Test
  void testMulOne() {
    Expression expr = new Mul(new Variable("x"), new Number(1));
    Expression expected = new Variable("x");
    assertEquals(expected, expr.simplify());
    expr = new Mul(new Number(1), new Variable("x"));
    assertEquals(expected, expr.simplify());
  }
  
  @Test
  void testSubEquals() {
    Expression expr = new Sub(new Variable("x"), new Variable("x"));
    assertEquals(new Number(0), expr.simplify());
  }
  
  @Test
  void testNoVariables() {
    Expression expr = new Mul(new Add(new Number(100), new Number(110)), new Number(500));
    assertEquals(new Number(105000), expr.simplify());
  }
}
