package cs2321;


public class TaskScheduling {
	/**
	 * Goal: Perform all the tasks using a minimum number of machines.
	 *
	 * @param tasks tasks[i][0] is start time for task i
	 *              tasks[i][1] is end time for task i
	 * @return The minimum number or machines
	 */

	public static int NumOfMachines(int[][] tasks) {
		HeapPQ<Integer, int[]> task = new HeapPQ<>();
		int machines = 0;
		ArrayList<Integer> Machines = new ArrayList<>();
		for (int i = 0; i <= tasks.length - 1; i++) {
			int[] temp = {tasks[i][0], tasks[i][1]};
			task.insert(i, temp);
		}
		while (!task.isEmpty()) {
			int[] smallest = task.removeMin().getValue();
			if (Machines.isEmpty()) {
				Machines.add(0, smallest[1]);
				machines += 1;
			}
			else {
				int j = -1;
				for(int i = 0; i <= Machines.size() - 1; i++) {
					if(Machines.get(i) <= smallest[0]){
						j = i;
					}
				}
				if(j != -1) {
					Machines.set(j, smallest[1]);
				}
				else {
					Machines.add(machines, smallest[1]);
					machines += 1;
				}
			}

		}
		return machines;
	}
}
