import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class Name: Exercise2
 * 
 * Specification:
 * Exercise2 class takes arguments input by the user via command line to read an input file
 * to a LinkedList (defined in a separate class) and then searches for anagrams of words. An 
 * array of LinkedLists is created and populated with words that have so far no anagrams. If 
 * an anagram is found, it is added to the LinkedList with its corresponding anagram. These 
 * LinkedLists are then sorted alphabetically via insertionSort, then the array is sorted via 
 * quickSort. Resulting sorted array is then output to a filename designated by the user.
 *
 * See LinkedList for LinkedList class definition.
 * 
 * @author Hunter Kimmett
 * @version 1.0
 * @since 2020-08-15 
 */

public class Exercise2 {
	
	/** Initializing variables for array of LinkedLists, initial LinkedList for reading file*/
	public static LinkedList[] arrayLL;
	public static  LinkedList initLL;

	/**Method reads file input via filename and creates a new LinkedList with each node 
	 * representing one line/word.
	 * @param filename, name of file input.
	 * @throws IOException for bad user input. */
	public static void readFile(String filename) throws IOException {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			String line;

			initLL = new LinkedList();
			while ((line = reader.readLine()) != null) {
				initLL.insertAtEnd(new LLWord(line));
			}

			reader.close();
		} catch (Exception e){
			System.err.println("Ensure argument" + filename + " is a valid filename.");
			System.exit(1);
		}

	}

	/** Method implements Quick Sort algorithm to sort an array by using recursion and 
	 * divide and conquer. This method divides the array in two around a pivot, where 
	 * any value larger than the pivot is placed on the higher side of the pivot and 
	 * any value smaller is placed to the left. This process is then done recursively 
	 * on both sides of the pivot until the array is sorted. Helper method partition is
	 * used to perform the sorting around the pivot.
	 * Source of algorithm: https://stackabuse.com/sorting-algorithms-in-java/ */
	public static void quickSort(LinkedList[] array, int begin, int end) {
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
	static int partition(LinkedList[] array, int begin, int end) {
		int pivot = end;

		int counter = begin;
		for (int i = begin; i < end; i++) {
			if (array[i].getHead().getWord().compareTo(array[pivot].getHead().getWord()) < 0) {
				LinkedList temp = array[counter];
				array[counter] = array[i];
				array[i] = temp;
				counter++;
			}
		}
		LinkedList temp = array[pivot];
		array[pivot] = array[counter];
		array[counter] = temp;

		return counter;
	}

	/**Method takes an input array and filename and writes each element of the array to a 
	 * line in an output file with the filename.
	 * @param arr, input array to be written to output file.
	 * @param filename, name of file output.
	 * @throws IOException for bad user input. */
	public static void writeFile(LinkedList[] arr, String filename) throws IOException {
		try {
			BufferedWriter writer =  new BufferedWriter(new FileWriter(filename));

			for (int i = 0; i < arr.length; i++) {
				writer.write(arr[i].returnFromHead());
				writer.newLine();
			}
			writer.close();
		}
		catch (Exception e){
			System.err.println("Ensure argument" + filename + " is a valid filename.");
			System.exit(1);
		}

	}

	public static void main(String[] args) throws IOException {

		// Input args from command line
		String in = args[0];
		String out = args[1];

		// Reading file to LinkedList
		readFile(in);
		
		// Starting timer, getting anagram array of LinkedLists
		long startTime = System.nanoTime();
		arrayLL = initLL.getAnagrams();
		
		// Removing null values from array
		ArrayList<LinkedList> countList = new ArrayList<LinkedList>();
		for (LinkedList l : arrayLL)
			if (!(l == null))
				countList.add(l);

		LinkedList[] finalArray = countList.toArray(new LinkedList[countList.size()-1]);
		
		// Sorting array and stopping timer
		quickSort(finalArray, 0, finalArray.length-1);
		long endTime = System.nanoTime();

		// Printing timer and outputting file
		double duration = (endTime - startTime)/1000000.0;
		System.out.println("Anagram algorithm run time: " + duration + " ms.");
		writeFile(finalArray, out);
	}

}
