package cs2321;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import net.datastructures.Entry;
import net.datastructures.PriorityQueue;

public class UnorderedPQTest {
	PriorityQueue<String, Integer> unordpq;
	
	@Before
	public void setUp() throws Exception {
		//unordpq = new UnorderedPQ<String, Integer>();
		unordpq = new OrderedPQ<String, Integer>();
		unordpq.insert("Bulbous Bouffant", 16);
		unordpq.insert("Gazebo", 6);
		unordpq.insert("Balooga", 7);
		unordpq.insert("Galoshes", 8);
		unordpq.insert("Eskimo", 6);
		unordpq.insert("Mukluks", 7);
		unordpq.insert("Macadamia", 9);
	}

	@Test
	public void testSize() {
		assertEquals("Not yet implemented", 7, unordpq.size() );
	}

	@Test
	public void testIsEmpty() {
		assertEquals("Not yet implemented", false, unordpq.isEmpty() );
	}

	@Test
	public void testInsert() {
		int s = unordpq.size();
		unordpq.insert("Hellow", 0);
		assertEquals("Not yet implemented", false, s == unordpq.size() );
	}

	@Test
	public void testMin() {
		Entry<String, Integer> test = unordpq.min();
		assertEquals("Not yet implemented", "Balooga", test.getKey());
	}

	@Test
	public void testRemoveMin() {
		Entry<String, Integer> e;
		String[] expected= {
				"Balooga", 
				"Bulbous Bouffant",
				"Eskimo", 
				"Galoshes", 
				"Gazebo", 
				"Macadamia",
				"Mukluks"
		};
		
		int i=0;
		while(!unordpq.isEmpty()){
			e = unordpq.removeMin();
			assertEquals(expected[i],  e.getKey());
			i++;
		}
	}
}
