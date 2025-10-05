package sys.pro;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/** DerivativeTest. */
public class DerivativeTest {
    @Test
    void testAdd() {
        assertEquals(
                new Add(new Number(1), new Number(0)),
                new Add(new Variable("x"), new Variable("y")).derivative(new Variable("x")));
    }

    @Test
    void testSub() {
        assertEquals(
                new Sub(new Number(1), new Number(0)),
                new Sub(new Variable("x"), new Variable("y")).derivative(new Variable("x")));
    }

    @Test
    void testMul() {
        assertEquals(
                new Add(
                        new Mul(new Number(1), new Variable("y")),
                        new Mul(new Variable("x"), new Number(0))),
                new Mul(new Variable("x"), new Variable("y")).derivative(new Variable("x")));
    }

    @Test
    void testDiv() {
        assertEquals(
                new Div(
                        new Sub(
                                new Mul(new Number(1), new Variable("y")),
                                new Mul(new Variable("x"), new Number(0))),
                        new Mul(new Variable("y"), new Variable("y"))),
                new Div(new Variable("x"), new Variable("y")).derivative(new Variable("x")));
    }

    @Test
    void testExpr() {
        Expression expected = new Add(
            new Number(1),
            new Add(
                new Number(1),
                new Add(
                    new Number(0),
                    new Add(
                        new Number(0),
                        new Add(
                            new Number(0),
                            new Sub(
                                new Add(
                                    new Mul(
                                        new Number(0),
                                        new Variable("x")),
                                    new Mul(
                                        new Number(2),
                                        new Number(1))),
                                new Add(
                                    new Number(1),
                                    new Div(
                                        new Sub(
                                            new Mul(
                                                new Number(
                                                    1),
                                                new Variable(
                                                    "x")),
                                            new Mul(
                                                new Variable(
                                                    "x"),
                                                new Number(
                                                    1))),
                                        new Mul(
                                            new Variable(
                                                "x"),
                                            new Variable(
                                                "x"))))))))));

        Expression original = new Add(
            new Variable("x"),
            new Add(
                new Variable("x"),
                new Add(
                    new Variable("y"),
                    new Add(
                        new Variable("z"),
                        new Add(
                            new Number(1),
                            new Sub(
                                new Mul(
                                    new Number(2),
                                    new Variable("x")),
                                new Add(
                                    new Variable("x"),
                                    new Div(
                                        new Variable("x"),
                                        new Variable(
                                            "x"))))))))); 

        assertEquals(expected, original.derivative(new Variable("x")));
    }
}
