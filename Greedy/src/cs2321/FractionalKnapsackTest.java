package cs2321;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FractionalKnapsackTest {

	double[][] sack = {{2, 5}, {3, 6}, {1, 2}, {1, 1}, {1, 4}, {2, 2}, {2, 7}};
	double[][] sack2 = {};
	double[][] sack3 = {{3, 6}, {1, 1}};
	double[][] sack4 = {{1, 3}, {1, 4}, {2, 5}, {3, 7}, {4, 7}, {6, 9}, {7, 8}};

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testMaximumValue() {
		assertEquals(7, FractionalKnapsack.MaximumValue(sack2, 10));
	}

}
