package sys.pro;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/** PrintTest. */
public class PrintTest {
    @Test
    void testExpr() {
        String str = "((((x + x) / x) + y) * 8)";
        assertEquals(str, Parser.parse(str).print());
    }
}
