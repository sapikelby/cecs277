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
 * Sorts an ArrayList of Integers by swapping adjacent
 * Integers if the left key is larger than the right key 
 * Inherits protected/public elements from MySort
 */
public class BubbleSort extends MySort
{
	/**
	 * Populates member array list mNumbers with the elements from numbers 
	 * sets mElapsedTime and mNumComparisons to 0
	 * @param numbers is the ArrayList of Integers being sorted
	 */
	public BubbleSort(ArrayList<Integer> numbers)
	{
		mElapsedTime = 0;
		mNumComparisons = 0;
		mNumbers = new ArrayList<Integer>(numbers.size()); 
		for (int i = 0; i < numbers.size(); i++) 
		{
			mNumbers.add(numbers.get(i));
		}
	}
	
	/**
	 * Returns the name of the sorting algorithm: Bubble Sort
	 * @return	sort method's name as a type String
	 */
	@Override
	public String askName()
	{
		return "Bubble Sort";
	}
	
	/**
	 * Sorts the ArrayList by swapping adjacent Integers if 
	 * the left key is larger than the right key 
	 * Bubble Sort is optimized by decrementing the number of 
	 * Integers that need to be compared after each iteration 
	 * Increments the number of comparisons 
	 * Measures the elapsed time
	 */
	@Override
	public void Sort() 
	{
		boolean swap = true;
		int temp = 0; 
		int j = 0; // used to decrement arrayList traversal
		Date before = new Date();

		while (swap) {
			swap = false;
			j++;
			for (int i = 0; i < mNumbers.size() - j; i++) 
			{
				// swapping keys (numbers)
				if (mNumbers.get(i) > mNumbers.get(i + 1)) 
				{
					swap = true;
					temp = mNumbers.get(i);
					mNumbers.set(i, mNumbers.get(i + 1));
					mNumbers.set(i + 1, temp);
				}

				mNumComparisons++; // increment num of comparisons
			}
		}

		Date after = new Date();
		//printList();
		mElapsedTime = after.getTime() - before.getTime();
	}
}
