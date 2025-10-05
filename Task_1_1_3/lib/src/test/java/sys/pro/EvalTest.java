package sys.pro;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/** EvalTest. */
public class EvalTest {
  @Test
  void testAdd() {
    Expression expr = new Add(new Variable("x"), new Variable("y"));
    assertEquals(120, expr.eval("x = 10; y = 110"));
  }
  
  @Test
  void testSub() {
    Expression expr = new Sub(new Variable("x"), new Variable("y"));
    assertEquals(100, expr.eval("x = 110; y = 10"));
  }
  
  @Test
  void testMul() {
    Expression expr = new Mul(new Variable("x"), new Variable("y"));
    assertEquals(1100, expr.eval("x = 110; y = 10"));
  }
  
  @Test
  void testDiv() {
    Expression expr = new Div(new Variable("x"), new Variable("y"));
    assertEquals(11, expr.eval("x = 110; y = 10"));
  }
  
  @Test
  void testExpr() {
    Expression expr = new Sub(
        new Mul(new Add(new Div(new Add(new Variable("x"), new Variable("x")), new Variable("x")), new Variable("y")),
            new Number(8)),
        new Variable("y"));
    assertEquals(121, expr.eval("x = 10; y = 15;"));
  }
  
  @Test
  void testIncorrectInput() {
    Expression expr = new Sub(
        new Mul(new Add(new Div(new Add(new Variable("x"), new Variable("x")), new Variable("x")), new Variable("y")),
            new Number(8)),
        new Variable("y"));
    assertThrows(IllegalArgumentException.class, () -> expr.eval("x = 10; y ="));
  }
}
