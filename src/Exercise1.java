import java.io.FileWriter;
import java.util.Random;

/**
 * Class Name: Exercise1
 * 
 * Specification:
 * Exercise1 class takes arguments input by the user via command line to create an array of 
 * integers in ascending, descending, or random order depending on input. The array is then 
 * sorted in a method input by the user, which will be bubble, insertion, merge or quick. After 
 * the array is sorted it is then output to a file along with the time taken for the sorting 
 * algorithm to complete.
 * 
 * All sorting algorithms are from  the following source:
 * 
 * https://stackabuse.com/sorting-algorithms-in-java/
 * 
 * @author Hunter Kimmett
 * @version 1.0
 * @since 2020-08-15 
 */

public class Exercise1 {

	/** Initializing variables, number of variables and the array to be made and sorted.*/
	private static int n;
	private static int[] array;

	/** Method instantiates array to size n, fills the first element with a random integer 
	 * between 0 and 10, then all other elements are increments of the previous element 
	 * plus a random integer between 0 and 10.*/
	public static void getAscendingArray() {
		Random r = new Random();

		array = new int[n];
		array[0] = r.nextInt(10);

		for (int i = 1; i < n; i++) {
			array[i] = array[i-1] + r.nextInt(10);
		}
	}

	/** Method instantiates array to size n, fills the first element with n times a random 
	 * integer between 0 and 10, then all other elements are decrements of the previous element 
	 * minus a random integer between 0 and 10.*/
	public static void getDescendingArray() {
		Random r = new Random();

		array = new int[n];
		array[0] = n * r.nextInt(10);

		for (int i = 1; i < n; i++) {
			array[i] = array[i-1] - r.nextInt(10);
		}
	}

	/** Method instantiates array to size n, fills all elements of the array with a random 
	 * integer. */
	public static void getRandomArray() {
		Random r = new Random();

		array = new int[n];
		for (int i = 0; i < n; i++) {
			array[i] = r.nextInt();
		}
	}

	/** Method implements Bubble Sort algorithm to sort an array by comparing two items in 
	 * the array and swapping if the first is larger than the second. This goes through the 
	 * entire array until it is sorted.
	 * Source of algorithm is StackAbuse, see class description.*/
	public static void bubbleSort(int[] a) {
		boolean sorted = false;
		int temp;
		while(!sorted) {
			sorted = true;
			for (int i = 0; i < a.length - 1; i++) {
				if (a[i] > a[i+1]) {
					temp = a[i];
					a[i] = a[i+1];
					a[i+1] = temp;
					sorted = false;
				}
			}
		}
	}

	/** Method implements Insertion Sort algorithm to sort an array by dividing the array 
	 * into two sub-arrays, one sorted and unsorted. The algorithm works by finding the 
	 * smallest value of the first 2 elements, sorts them, then moves to the right and 
	 * takes the next value and sorts it into a position in the sorted sub-array. This is 
	 * done for the entire array.
	 * Source of algorithm is StackAbuse, see class description.*/
	public static void insertionSort(int[] a) {
		for (int i = 1; i < a.length; i++) {
			int val = a[i];
			int j = i - 1;
			while(j >= 0 && val < a[j]) {
				a[j+1] = a[j];
				j--;
			}
			a[j+1] = val;
		}
	}

	/** Method implements Merge Sort algorithm to sort an array by using recursion and 
	 * divide and conquer. It creates 2 sub=arrays and then sorts each array, then merges
	 * the two arrays together. This is aided by the helper method merge, which evaluates 
	 * the two sub-arrays elements. If the value in one is smaller than the other, it gets
	 * put into the merged array first. This continues until both sub-arrays are merged, 
	 * and then all sub-arrays are merged within the array.
	 * Source of algorithm is StackAbuse, see class description.*/
	public static void mergeSort(int[] a, int left, int right) {
		if (right <= left) return;
		int mid = (left+right)/2;
		mergeSort(a, left, mid);
		mergeSort(a, mid+1, right);
		merge(a, left, mid, right);
	}

	/** Helper method for mergeSort, this method takes the divisions of an array and 
	 * creates temporary sub-arrays, which then are then sorted by determining if the 
	 * value in sub-array element is smaller than elements in the other, then it gets 
	 * put into the merged array first.*/
	public static void merge(int[] a, int left, int mid, int right) {
		// Lengths of arrays and initializing sub-arrays
		int lenL = mid - left + 1;
		int lenR = right - mid;
		int arrayL[] = new int [lenL];
		int arrayR[] = new int [lenR];

		// Copying from main array to sub-arrays
		for (int i = 0; i < lenL; i++)
			arrayL[i] = a[left+i];
		for (int i = 0; i < lenR; i++)
			arrayR[i] = a[mid+i+1];

		// Instantiating iterators for current index of sub-arrays
		int indexL = 0;
		int indexR = 0;

		// Merging arrays
		for (int i = left; i < right + 1; i++) {
			// If neither array is merged, merge smallest element
			if (indexL < lenL && indexR < lenR) {
				if (arrayL[indexL] < arrayR[indexR]) {
					a[i] = arrayL[indexL];
					indexL++;
				}
				else {
					a[i] = arrayR[indexR];
					indexR++;
				}
			}
			// If right sub-array is merged, merge left sub-array
			else if (indexL < lenL) {
				a[i] = arrayL[indexL];
				indexL++;
			}
			// If left sub-array is merged, merge right sub-array
			else if (indexR < lenR) {
				a[i] = arrayR[indexR];
				indexR++;
			}
		}
	}

	/** Method implements Quick Sort algorithm to sort an array by using recursion and 
	 * divide and conquer. This method divides the array in two around a pivot, where 
	 * any value larger than the pivot is placed on the higher side of the pivot and 
	 * any value smaller is placed to the left. This process is then done recursively 
	 * on both sides of the pivot until the array is sorted. Helper method partition is
	 * used to perform the sorting around the pivot.
	 * Source of algorithm is StackAbuse, see class description.*/
	public static void quickSort(int[] array, int begin, int end) {
		if (end <= begin) return;
		int pivot = partition(array, begin, end);
		quickSort(array, begin, pivot-1);
		quickSort(array, pivot+1, end);
	}

	/** Helper method for quickSort, places pivot at end of the array and then runs 
	 * through the array, if the value of an element is smaller than the pivot then it 
	 * is takes a position in an array determined by a counter, initialized to the 
	 * beginning of the partition. The method then returns where the counter was left, 
	 * so that the quickSort method can start at the next pivot.*/
	static int partition(int[] array, int begin, int end) {
		int pivot = end;

		int counter = begin;
		for (int i = begin; i < end; i++) {
			if (array[i] < array[pivot]) {
				int temp = array[counter];
				array[counter] = array[i];
				array[i] = temp;
				counter++;
			}
		}
		int temp = array[pivot];
		array[pivot] = array[counter];
		array[counter] = temp;

		return counter;
	}

	public static void main(String[] args) {

		// Parsing the integer argument to get the size of the array
		try {
			n = Integer.parseInt(args[1]);
		} catch (NumberFormatException e) {
			System.err.println("Argument " + args[1] + " must be an integer.");
			System.exit(1);
		}

		// Creating array based on array type input by user
		try { 
			String order = args[0];

			if (order.equals("ascending")) {
				getAscendingArray();
			} else if (order.equals("descending")) {
				getDescendingArray();
			} else if (order.equals("random")) {
				getRandomArray();
			} else {
				System.out.println("Argument " + order + " must be one of the following:");
				System.out.println("ascending, descending, random");
			}
		} catch (Exception e) {
			System.err.println("Argument " + args[0] + " must be one of the following:");
			System.err.println("ascending, descending, random");
			System.exit(1);
		}

		// Initializing timers
		long startTime = 0;
		long endTime = 0;

		// Sorting arrays based on input from user, counting time
		try {
			String algorithm = args[2];

			if (algorithm.equals("bubble")) {
				startTime = System.nanoTime();
				bubbleSort(array);
				endTime = System.nanoTime();
			} else if (algorithm.equals("insertion")) {
				startTime = System.nanoTime();
				insertionSort(array);
				endTime = System.nanoTime();
			} else if (algorithm.equals("merge")) {
				startTime = System.nanoTime();
				mergeSort(array, 0, n-1);
				endTime = System.nanoTime();
			} else if (algorithm.equals("quick")) {
				startTime = System.nanoTime();
				quickSort(array, 0, n-1);
				endTime = System.nanoTime();
			} else {
				System.out.println("Argument " + algorithm + " must be one of the following:");
				System.out.println("bubble, insertion, merge, quick");
			}
		} catch (Exception e) {
			System.err.println("Argument " + args[2] + " must be one of the following:");
			System.err.println("bubble, insertion, merge, quick");
			System.exit(1);
		}

		// Printing how long the sort took
		double duration = (endTime - startTime)/1000000.0;
		System.out.println("Sort algorithm run time: " + duration + " ms.");

		// Output to .txt file, based on user input
		try {
			String output = args[3];
			FileWriter fw = new FileWriter(output);

			fw.write("Sort algorithm run time: " + duration + " ms.\n");

			for (int i = 0; i < n; i++) {
				fw.write(array[i] + "\n");
			}
			fw.close();
		} catch (Exception e) {
			System.err.println("Ensure argument" + args[3] + " is a valid filename.");
			System.exit(1);
		}

	}

}
