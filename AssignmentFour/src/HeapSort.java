/**
*
* CECS 277 Spring 2014.   Assignment 4
*
* @author Kelby Sapien 3260
* @author Paul Kim 6816
*
*/
import java.util.ArrayList;
import java.util.Date;

/**
 * Sorts array list by establishing heap property on an array list, then 
 * swapping root node (highest value) with rightmost node on the lowest level
 * Inherits protected/public elements from MySort
 */
public class HeapSort extends MySort{

	/**
	 * Populates member array list mNumbers with the elements from numbers 
	 * sets mElapsedTime and mNumComparisons to 0
	 * @param numbers is the ArrayList of Integers being sorted
	 */
	public HeapSort(ArrayList<Integer> numbers)
	{
		mElapsedTime = 0;
		mNumComparisons = 0;
		mNumbers = new ArrayList<Integer>(numbers.size()); 
		for(int i=0; i<numbers.size(); i++)
		{
			mNumbers.add(numbers.get(i));
		}
	}
	
	/**
	 * Returns the name of the sorting algorithm: Heap Sort
	 * @return	sort method's name as a type String
	 */
	public String askName()
	{
		return "Heap Sort"; 
	}
	/**
	 * calls the heapSort function in order to sort array list
	 */
	public void Sort() 
	{
		mNumbers = heapSort(mNumbers);
	}
	
	/**
	 * Once heapify is called and largest elements is at the root --
	 * Swaps the root with the last node in the heap and removes it
	 * from the heap, rebuilds heap until all elements are sorted
	 * @param unsortedList is the unsorted array list being sorted
	 * @return unsortedList as a sorted list once algorithm executes
	 */
	public ArrayList<Integer> heapSort(ArrayList<Integer> unsortedList)
	{
		Date before = new Date();
		int count = unsortedList.size();
		heapify(unsortedList, count);
		int end = count-1;
		while(end > 0)
		{
			swap(unsortedList, end, 0);
			end = end - 1;
			siftDown(unsortedList, 0, end);
		}
		Date after = new Date();
		mElapsedTime = after.getTime() - before.getTime();
		return unsortedList;
	}
	/**
	 * Builds heap using siftDown to establish heap property in each subtree
	 * @param unsortedList is the unsorted array list being sorted
	 * @param count is the array list size
	 */
	public void heapify(ArrayList<Integer> unsortedList, int count)
	{
		int start = count/2 - 1;
		while(start >= 0)
		{
			siftDown(unsortedList, start, count - 1);
			start -= 1;
		}
	}
	/**
	 * Moves values down the tree by successively exchanging higher value
	 * with the smaller of its two children
	 * Increments mNumComparisons on each key comparison
	 * @param unsortedList is the unsorted array list being sorted
	 * @param start is the starting index
	 * @param end is the ending index
	 */
	public void siftDown(ArrayList<Integer> unsortedList, int start, int end)
	{
		int root = start;
		while(root*2+1 <= end)
		{
			int child = root*2+1;
			int swap = root;
			if(unsortedList.get(swap) < unsortedList.get(child))
			{
				swap = child;
			}
			mNumComparisons++;
			if(child+1 <= end && unsortedList.get(swap) < unsortedList.get(child+1))
			{
				swap = child+1;
			}
			mNumComparisons++;
			if(swap != root)
			{
				swap(unsortedList, root, swap);
				root = swap;
			}
			else
			{
				return;
			}
			
			
		}
	}
	/**
	 * Auxiliary swap function called when rebuilding heap property
	 * @param unsortedList is the unsorted array list being sorted
	 * @param swapOne first item to be swapped
	 * @param swapTwo second item to be swapped
	 */
	public void swap(ArrayList<Integer> unsortedList, int swapOne, int swapTwo)
	{
		int holder = unsortedList.get(swapOne);
		unsortedList.set(swapOne, unsortedList.get(swapTwo));
		unsortedList.set(swapTwo, holder);
	}
}	
