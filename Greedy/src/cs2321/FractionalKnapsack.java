package cs2321;

/**
 * @author:
 *
 */
public class FractionalKnapsack {

   
	/**
	 * Goal: Choose items with maximum total benefit but with weight at most W.
	 *       You are allowed to take fractional amounts from items.
	 *       
	 * @param items items[i][0] is weight for item i
	 *              items[i][1] is benefit for item i
	 * @param knapsackWeight
	 * @return The maximum total benefit. Please use double type operation. For example 5/2 = 2.5
	 * 		 
	 */
	public static double MaximumValue(double[][] items, int knapsackWeight) {
		MaxHeap<Double, double[]> stuff = new MaxHeap<>();
		for (int i = 0; i <= items.length - 1; i++) {
			double[] temp = {items[i][0], items[i][1], 0};
			stuff.insert((double) items[i][1] / items[i][0], temp);
		}
		int weight = 0;
		ArrayList<double[]> taking = new ArrayList<>();
		int i = 0;
		while (weight < knapsackWeight) {
			double temp = stuff.max().getKey();
			double[] temp2 = stuff.removeMax().getValue();
			double[] temp3 = {temp, min(temp2[0], knapsackWeight - weight)};
			taking.add(i, temp3);
			weight += min(temp2[0], knapsackWeight - weight);
		}
		double value = 0;
		for(double[] s : taking) {
			value += s[0];
		}
		return value;
	}

	public static double min(double i, double j) {
		if(i < j) return i;
		else return  j;
	}
}
