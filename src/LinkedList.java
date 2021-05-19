import java.util.Arrays;

/**
 * Class Name: LinkedList
 * 
 * Specification:
 * LinkedList class creates a LinkedList with its constructor which can be contain nodes from 
 * the LLWord class. LinkedList's constructor sets the default head value as null, insertAtEnd
 * adds a new LLWord to the end of the list, findLastNode finds the end of the list, 
 * returnFromHead returns all nodes in order from the head as a String separated by a space, 
 * getCount returns the number of nodes in the list, deletNode deletes a node in select pointer.
 * getHead, getSortHead, setHead, and setSortHead are getters and setters for respective variables.
 * The insertionSort and helper sortedInsert use insertion sort to arrange the order of a list.
 * getAnagrams method creates an array of lists of words that are anagrams.
 * 
 * LLWord class serves as a node, with getters for variables word, next, and charArray and setters
 * for head and next. Constructor sets the value for head and charArray.
 *
 * See LinkedList for LinkedList class definition.
 * 
 * @author Hunter Kimmett
 * @version 1.0
 * @since 2020-08-15 
 */

public class LinkedList{

	// Initializing variables, head for the head of the list, sortHead for head of sorted list
	private LLWord head;
	private LLWord sortHead;

	/** Constructor sets head as null*/
	LinkedList(){
		setHead(null);	
	}

	/** Method inserts a LLWord node at the end of the list*/
	public void insertAtEnd(LLWord word) {
		if (head == null) {
			this.setHead (word);
			return;
		}
		LLWord cursor = findLastNode();
		cursor.setNext(word);

	}

	/** Method iterates through list to find final node. */
	public LLWord findLastNode() {
		LLWord cursor = head;
		while (cursor.getNext() != null)
			cursor = cursor.getNext();
		return cursor;
	}

	/** Method returns nodes in list as a String, separated by spaces. */
	public String returnFromHead () {
		String listString = "";
		LLWord cursor = head;
		while (cursor != null) {
			listString += cursor.getWord() + " ";
			cursor = cursor.getNext();
		}
		return listString;
	}

	/** Method creates an array of linked lists of words that are anagrams. This is done by 
	 * comparing nodes' charArray values, if it is unique a new list is added to the array, if
	 * it isn't it is added to a corresponding previous list, and then deletes the node. This 
	 * method implements the deleteNode and insertAtEnd methods to do this.*/
	public LinkedList[] getAnagrams() {
		// If there is no head, return null
		if (head==null) {
			return null;
		}
		
		// Creating array of max size and instantiating cursor and counter
		LinkedList[] array = new LinkedList[this.getCount()];
		LLWord cursor = head;
		int counter = 0;
		
		// While loop to generate linked lists to populate array
		while(cursor.getNext() != null) {
			
			// Create new LinkedList and insert cursor in it
			LinkedList anaList = new LinkedList();
			anaList.insertAtEnd(new LLWord(cursor.getWord()));
			
			// Search outer list for anagrams, adding to LinkedList if so
			LLWord anaCursor = cursor.getNext();
			while(anaCursor != null) {
				if (Arrays.equals(cursor.getCharArray(), anaCursor.getCharArray())) {
					anaList.insertAtEnd(new LLWord(anaCursor.getWord()));
					this.deleteNode(anaCursor);
					
				} else {
					anaCursor = anaCursor.getNext();
				}
				
			}

			// Sort LinkedList
			anaList.insertionSort(anaList.getHead());

			// Populate array, increase counter, get next node.
			array[counter] = anaList;
			counter++;
			cursor = cursor.getNext();
		}
		
		return array;
	}

	/** Returns number of nodes in a list. */
	public int getCount() { 
		LLWord temp = head; 
		int count = 0; 
		while (temp != null) 
		{ 
			count++; 
			temp = temp.getNext(); 
		} 
		return count; 
	}
	
	/** Deletes input node and assigns next node in list to original node's value. */
	public void deleteNode(LLWord node) {
		if (node.getNext() == null) {
			node.setWord(null);
		} else {
			LLWord temp = node.getNext(); 
	        node.setWord(temp.getWord()); 
	        node.setNext(temp.getNext());
		}

        System.gc();
	}
	
	/** Method performs insertion sort on a list by taking a head input and using it as the key.
	 * The method then iterates across list and adds sorted nodes to a sorted list with the 
	 * sortedInsert method. The head of the sorted list is then returned. 
	 * Source of algorithm: https://www.geeksforgeeks.org/insertion-sort-for-singly-linked-list/ */
    public void insertionSort(LLWord node) {
        // Initialize head to null and key to head
        sortHead = null; 
        LLWord key = node; 
        
        // Iterate through list adding nodes to sorted list
        while (key != null)  { 
            // Store current.next in next, add node to sorted list, move key to next
        	LLWord next = key.getNext();  
        	sortedInsert(key);
            key = next; 
        } 
        
        // return sorted head
        head = sortHead; 
    }
    
    /** */
    public void sortedInsert(LLWord node) {
    	// If head is null
        if (sortHead == null || sortHead.getWord().compareTo(node.getWord()) >= 0)    { 
            node.setNext(sortHead); 
            sortHead = node; 
        } else { 
        	
        	// Finding values and sorting them
            LLWord key = sortHead; 
            while (key.getNext() != null && key.getNext().getWord().compareTo(node.getWord()) < 0)   { 
                key = key.getNext(); 
            } 
            
            // Finding next node
            node.setNext(key.getNext()); 
            key.setNext(node); 
        } 
    }

    /** Getter method for head*/
	public LLWord getHead() {
		return head;
	}

	/** Setter method for head*/
	public void setHead(LLWord head) {
		this.head = head;
	}

	/** Getter method for sortHead*/
	public LLWord getSortHead() {
		return sortHead;
	}
	
	/** Setter method for sortHead*/
	public void setSortHead(LLWord sortHead) {
		this.sortHead = sortHead;
	}

}

/** LinkedList node class*/
class LLWord {

	// Initializing variables, by default the word is null, next is pointer to next node, charArray 
	// an array of characters in word, sorted by Arrays.sort() (only time built-in method used)
	private String word = null;
	private LLWord next;
	private char[] charArray;

	/** Constructor sets word as input string, charArray as an array of characters in word, sorted.*/
	LLWord(String word){
		this.setWord(word);
		charArray = word.toCharArray();
		Arrays.sort(charArray);

	}

	/** Getter method for word*/
	public String getWord() {
		return word;
	}
	/** Setter method for word, also sets charArray*/
	public void setWord(String word) {
		this.word = word;
		if (word == null) {
			this.charArray = null;
		} else {
			this.charArray = word.toCharArray();
			Arrays.sort(charArray);
		}
	}

	/** Getter method for next*/
	public LLWord getNext() {
		return next;
	}

	/** Setter method for next*/
	public void setNext(LLWord next) {
		this.next = next;
	}

	/** Getter method for charArray*/
	public char[] getCharArray() {
		return charArray;
	}

}


