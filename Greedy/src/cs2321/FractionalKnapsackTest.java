package cs2321;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FractionalKnapsackTest {

	int[][] sack = {{4, 12}, {8, 32}, {2, 40}, {6, 30}, {1, 50}};
	int[][] sack2 = {};
	int[][] sack3 = {{3, 6}, {1, 1}};
	int[][] test = {};

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testMaximumValue() {
		assertEquals(124D, FractionalKnapsack.MaximumValue(sack, 10), 0);
		assertEquals(0D, FractionalKnapsack.MaximumValue(sack2, 10), 0);
		assertEquals(7D, FractionalKnapsack.MaximumValue(sack3, 10), 0);
	}

}
