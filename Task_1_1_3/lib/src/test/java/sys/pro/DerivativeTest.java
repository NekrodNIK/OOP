package sys.pro;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/** DerivativeTest. */
public class DerivativeTest {
  @Test
  void testAdd() {
    assertEquals(new Add(new Number(1), new Number(0)),
        new Add(new Variable("x"), new Variable("y")).derivative(new Variable("x")));
  }
}
