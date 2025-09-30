package sys.pro;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ParserTest {
  @Test
  void testAdd() {
    assertEquals(new Add(new Variable("x"), new Number(2)), Parser.parse("x + 2"));
  }

  @Test
  void testSub() {
    assertEquals(new Sub(new Variable("x"), new Number(2)), Parser.parse("x - 2"));
  }

  @Test
  void testMul() {
    assertEquals(new Mul(new Variable("x"), new Number(2)), Parser.parse("x * 2"));
  }

  @Test
  void testDiv() {
    assertEquals(new Div(new Variable("x"), new Number(2)), Parser.parse("x / 2"));
  }

  @Test
  void testComplexExpr() {
    assertEquals(
        new Sub(new Div(new Add(new Variable("x"), new Variable("y")), new Number(100)),
            new Mul(new Number(20), new Div(new Variable("x"), new Variable("y")))),
        Parser.parse("(x+y)/100-20*(x/y)"));
  }
}
