package cs2321;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import net.datastructures.AdaptablePriorityQueue;
import net.datastructures.Entry;

public class HeapPQTest {

	AdaptablePriorityQueue<String, Integer> heappq;
	
	@Before
	public void setUp() throws Exception {
		heappq = new HeapPQ<String, Integer>();
		heappq.insert("Bulbous Bouffant", 16);
		heappq.insert("Gazebo", 6);
		heappq.insert("Balooga", 7);
		heappq.insert("Galoshes", 8);
		heappq.insert("Eskimo", 6);
		heappq.insert("Mukluks", 7);
		heappq.insert("Macadamia", 9);

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
		while(!heappq.isEmpty()){
			e = heappq.removeMin();
			assertEquals(expected[i],  e.getKey());
			i++;
		}
	}


	@Test
	public void testSize() {
		assertEquals("Not yet implemented", 7, heappq.size());
	}
	

	@Test
	public void testIsEmpty() {
		assertEquals("Not yet implemented", false, heappq.isEmpty());
	}

	@Test
	public void testInsert() {
		int s = heappq.size();
		heappq.insert("Bazinga", 9);
		assertEquals("Not yet implemented", false, s == heappq.size());
	}

	@Test
	public void testMin() {
		assertEquals("Not yet implemented", "Balooga", heappq.min().getKey());
	}

	@Test
	public void testRemove() {
		int s = heappq.size();
		heappq.remove(heappq.min());
		assertEquals("Not yet implemented", true, s == heappq.size() + 1);
	}

	@Test
	public void testReplaceKey() {
		heappq.replaceKey(heappq.min(), "Baloo");
		assertEquals("Not yet implemented", "Baloo", heappq.min().getKey());
	}

	@Test
	public void testReplaceValue() {
		heappq.replaceValue(heappq.min(), 5);
		assertEquals("Not yet implemented", "5", heappq.min().getValue().toString());
	}
}
