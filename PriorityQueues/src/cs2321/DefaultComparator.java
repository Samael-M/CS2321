package cs2321;

import java.util.Comparator;

public class DefaultComparator<K> implements Comparator<K> {
	public int compare(K a, K b) throws ClassCastException {
//		if(a == b) { return 0; }
//		else if (a < b) { return -1; }
//		else if (a > b) { return 1; }
		return ((Comparable <K>) a).compareTo(b);
	}
}
