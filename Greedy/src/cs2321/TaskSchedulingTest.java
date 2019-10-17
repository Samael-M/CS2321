package cs2321;

import static org.junit.Assert.*;

import javafx.concurrent.Task;
import org.junit.Before;
import org.junit.Test;

public class TaskSchedulingTest {

	int[][] task = {{1, 3}, {1, 4}, {2, 5}, {3, 7}, {4, 7}, {6, 9}, {7, 8}};
//	int[][] task2 = {};
//	int[][] task3 = {{1, 3}, {3, 4}};
//	int[][] task4 = {{1, 3}, {1, 4}, {2, 5}, {3, 7}, {4, 7}, {6, 9}, {7, 8}};
//	int[][] task5 = {{2, 5}, {2, 5}, {1, 4}, {1, 4}};

	@Before
	public void setUp() throws Exception { }

	@Test
	public void testNumOfMachines() {

		assertEquals(3,TaskScheduling.NumOfMachines(task));
//		assertEquals(0,TaskScheduling.NumOfMachines(task2));
//		assertEquals(1,TaskScheduling.NumOfMachines(task3));
//		assertEquals(4,TaskScheduling.NumOfMachines(task5));
	}

}



