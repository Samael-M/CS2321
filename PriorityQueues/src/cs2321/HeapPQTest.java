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

//		heappq = new HeapPQ<Integer, String>();
//		heappq.insert(16, "Bulbous Bouffant");
//		heappq.insert(6, "Gazebo");
//		heappq.insert(7, "Balooga");
//		heappq.insert(8, "Galoshes");
//		heappq.insert(6, "Eskimo");
//		heappq.insert(7, "Mukluks");
//		heappq.insert(9, "Macadamia");
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
		fail("Not yet implemented"); // TODO
	}
	

	@Test
	public void testIsEmpty() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testInsert() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testMin() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testRemove() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testReplaceKey() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testReplaceValue() {
		fail("Not yet implemented"); // TODO
	}

}
