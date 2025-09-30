package sys.pro;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EvalTest {
    @Test
    void testExpr() {
        String str = "((x + x) / x + y) * 8 - y";
        assertEquals(121, Parser.parse(str).eval("x = 10; y = 15;"));
    }
}
