/**
*
* CECS 277 Spring 2014.   Assignment 4
*
* @author Kelby Sapien 3260
* @author Paul Kim 6816
*
*/

import java.util.ArrayList;
import java.util.Random;

/**
 * Sorts an ArrayList of Integers by swapping adjacent 
 * Integers if the left key is larger than the right key 
 * Inherits protected/public elements from MySort
 */
public class QuickSortRandomized extends QuickSort
{
	/**
	 * calls Super class's constructor and fills an ArrayList with Integers 
	 * @param numbers is the ArrayList of Integers being sorted
	 */
	public QuickSortRandomized(ArrayList<Integer> numbers)
	{
		super(numbers);
	}
	
	/**
	 * Overrides askName method in QuickSort class 
	 * Returns the name of the sorting algorithm: Quick Sort (R)
	 * @return	sort method's name as a type String
	 */
	@Override
	public String askName()
	{
		return "Quick Sort (R)";
	}
	
	/**
	 * Overrides getPivotIndex method in QuickSort class and returns a random Integer
	 * within the range [left, right] <p>
	 * @param left is the first index position of a list
	 * @param right is the last index position of a list
	 * @return returns random Integer in the range of left and right, inclusive.
	 */
	public int getPivotIndex(int left, int right)
	{
		Random rand = new Random();
		return left + rand.nextInt(right - left + 1);
	}
}
