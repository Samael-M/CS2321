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
	public static double MaximumValue(int[][] items, int knapsackWeight) {
		MaxHeap<Double, double[]> stuff = new MaxHeap<>(items.length);
		for (int i = 0; i <= items.length - 1; i++) {
			double[] temp = {items[i][0], items[i][1], 0}; //{weight, benefit}
			stuff.insert((double) items[i][1] / items[i][0], temp); //(valueOfThing(key), {weight, benefit}(value))
		}
		return mv(stuff, knapsackWeight);
	}

	public static double mv(MaxHeap<Double, double[]> stuff, int maxWeight) {
		double weight = 0;
		ArrayList<double[]> taking = new ArrayList<>(stuff.size());
		int i = 0;
		while (weight < maxWeight && !stuff.isEmpty()) {
			double temp = stuff.max().getKey(); //(valueOfThing)
			double[] temp2 = stuff.removeMax().getValue(); //{weight, benefit}
			double[] temp3 = {temp, min(temp2[0], maxWeight - weight)}; //{valueOfThing, amount to bring}
			taking.add(i, temp3);
			weight += min(temp2[0], maxWeight - weight);
			i++;
		}
		double value = 0;

		for(int j = 0; j < taking.size(); j++) {
			value += taking.get(j)[0] * taking.get(j)[1];
		}
		return value;
	}

	public static double min(double i, double j) {
		if(i < j) return i;
		else return  j;
	}
}
