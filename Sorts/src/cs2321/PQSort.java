package cs2321;

import net.datastructures.*;

/**
 * A class that performs the generic PQ Sort:
 *
 * @author CS2321 Instructor
 * @param <K>
 */
public class PQSort<K extends Comparable<K>> {
	
	/*
	 * PQSort - insert every element in array to PQ, 
	 * then call PQ's removeMin repeatedly, and overwrite the data in the array
	 */
	@TimeComplexity("O(n)")
	/**
	 * both for loops are O(n) going from 0 to n. together they would be O(n + n) which is just O(n)
	 */
	protected void sort(K[] kArray, PriorityQueue<K,K> pq) {
		for(int i = 0; i <= kArray.length - 1; i++) {
			pq.insert(kArray[i], null);
		}
		for(int i = 0; i <= kArray.length - 1; i++) {
			kArray[i] =  pq.removeMin().getKey();
		}
	}

	

}
