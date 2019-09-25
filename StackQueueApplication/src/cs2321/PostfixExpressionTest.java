package cs2321;

import static org.junit.Assert.*;

import javafx.geometry.Pos;
import org.junit.Before;
import org.junit.Test;

public class PostfixExpressionTest {

    PostfixExpression posfe;

    @Before
    public void setUp() throws Exception {
        posfe = new PostfixExpression();
    }

    @Test
    public void testEvaluate1() {
        org.junit.Assert.assertEquals((Object) 8, (Object) posfe.evaluate("5 3 +"));
    }

    @Test
    public void testEvaluate2() {
        org.junit.Assert.assertEquals((Object) 2, (Object) posfe.evaluate("5 3 -"));
    }

    @Test
    public void testEvaluate3() {
        org.junit.Assert.assertEquals(-2, (Object) posfe.evaluate("3 -5 +"));
    }

    @Test
    public void testEvaluate4() {
        org.junit.Assert.assertEquals(80, (Object) posfe.evaluate("5 7 9 + *"));
    }

    @Test
    public void testEvaluate5() {
        org.junit.Assert.assertEquals(-2940, (Object) posfe.evaluate("5 -7 * 3 9 9 * + *"));
    }

    @Test
    public void testEvaluate6() {
        org.junit.Assert.assertEquals(16, (Object) posfe.evaluate("4 4 + 4 4 + +"));
    }

    @Test
    public void testEvaluate7() {
        org.junit.Assert.assertEquals(8, (Object) posfe.evaluate("9 3 1 - -3 * + 10 2 / +"));
    }

    @Test
    public void testEvaluate8() {
        org.junit.Assert.assertEquals(-500, (Object) posfe.evaluate("1 2 3 4 6 7 8 / + * 9 * + * -"));
    }

}
