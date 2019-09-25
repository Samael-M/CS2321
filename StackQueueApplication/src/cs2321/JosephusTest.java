package cs2321;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class JosephusTest {

    Josephus jojo;
    String[] test1 = new String[]{"A"};
    String[] test2 = new String[]{"A", "B", "C", "D", "E", "F"};
    String[] testResult1 = new String[]{"A"};
    String[] testResult2 = new String[]{"A", "B", "C", "D", "E", "F"};
    String[] testResult3 = new String[]{"C", "F", "D", "B", "E", "A"};
    String[] testResult4 = new String[]{"F", "A", "C", "B", "E", "D"};

    @Before
    public void setUp() throws Exception {
        jojo = new Josephus();
    }

    @Test
    public void testOrder1() { org.junit.Assert.assertTrue(jojo.order(test1, 3).equalsArray(testResult1)); }

    @Test
    public void testOrder2() { org.junit.Assert.assertTrue(jojo.order(test2, 1).equalsArray(testResult2)); }

    @Test
    public void testOrder3() { org.junit.Assert.assertTrue(jojo.order(test2, 3).equalsArray(testResult3)); }

    @Test
    public void testOrder4() { org.junit.Assert.assertTrue(jojo.order(test2, 6).equalsArray(testResult4)); }

}
