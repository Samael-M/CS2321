package cs2321;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

/**
 * A test driver for Sorts.
 * <p>
 * Course: CS2321 Section ALL
 * Assignment: #5
 *
 * @author Mark Washington
 */

public class SortTiming
{

    public static void main(String[] args) throws IOException
    {
        PrintWriter csvWriter = new PrintWriter(new File("AllSorts.csv"));
        Integer[] timingTestArray = new Integer[100000];

        Random random = new Random();

        for (int i = 0; i < timingTestArray.length; i++)
        {
            timingTestArray[i] = random.nextInt(100000);
        }

        Sorter<Integer> selectionSorter = new InPlaceSelectionSort<>();
        Sorter<Integer> insertionSorter = new InPlaceInsertionSort<>();
        Sorter<Integer> unorderedPQSorter = new UnorderedPQSort<>();
        Sorter<Integer> orderedPQSorter = new OrderedPQSort<>();
        Sorter<Integer> heapPQSorter = new HeapPQSort<>();
        Sorter<Integer> heapSorter = new InPlaceHeapSort<>();
        Sorter<Integer> quickSorter = new QuickSort<>();
        Sorter<Integer> mergeSorter = new MergeSort<>();

        System.gc();

        long selectionSortTime = sortTiming(selectionSorter, timingTestArray.clone());
        long insertionSortTime = sortTiming(insertionSorter, timingTestArray.clone());
        long unorderedPQSortTime = sortTiming(unorderedPQSorter, timingTestArray.clone());
        long orderedPQSortTime = sortTiming(orderedPQSorter, timingTestArray.clone());
        long heapPQSortTime = sortTiming(heapPQSorter, timingTestArray.clone());
        long heapSortTime = sortTiming(heapSorter, timingTestArray.clone());
        long quickSortTime = sortTiming(quickSorter, timingTestArray.clone());
        long mergeSortTime = sortTiming(mergeSorter, timingTestArray.clone());


        // Write all sorts to csv file
        csvWriter.println("Sort,Time");

        csvWriter.println("Selection Sort," + selectionSortTime);
        System.out.println("Selection sort finished");

        csvWriter.println("Insertion Sort," + insertionSortTime);
        System.out.println("Insertion sort finished");

        csvWriter.println("Unordered PQ Sort," + unorderedPQSortTime);
        System.out.println("Unordered PQ sort finished");

        csvWriter.println("Ordered PQ Sort," + orderedPQSortTime);
        System.out.println("Ordered PQ sort finished");

        csvWriter.println("Heap PQ Sort," + heapPQSortTime);
        System.out.println("Heap PQ sort finished");

        csvWriter.println("In Place Heap Sort," + heapSortTime);
        System.out.println("In Place Heap Sort finished");

        csvWriter.println("Quick Sort," + quickSortTime);
        System.out.println("Quick sort finished");

        csvWriter.println("Merge Sort," + mergeSortTime);
        System.out.println("Merge sort finished");

        csvWriter.close();

        // Write fast sorts to csv file
        csvWriter = new PrintWriter(new File("FastSorts.csv"));
        csvWriter.println("Sort,Time");
        csvWriter.println("Heap PQ Sort," + heapPQSortTime);
        System.out.println("Heap PQ sort finished");

        csvWriter.println("In Place Heap Sort," + heapSortTime);
        System.out.println("In Place Heap Sort finished");

        csvWriter.println("Merge Sort," + mergeSortTime);
        System.out.println("Merge sort finished");

        csvWriter.println("Quick Sort," + quickSortTime);
        System.out.println("Quick sort finished");

        csvWriter.close();
    }

    public static long sortTiming(Sorter<Integer> sorter, Integer[] sortTimingArray)
    {
        long startTime;
        long endTime;
        long deltaTime;

        startTime = System.nanoTime();

        sorter.sort(sortTimingArray);

        endTime = System.nanoTime();
        deltaTime = (endTime - startTime) / 1000000;

        return deltaTime;
    }

}
