/**
*
* CECS 277 Spring 2014.   Assignment 4
*
* @author Kelby Sapien 3260
* @author Paul Kim 6816
*
*/

import java.util.Date;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Sorts an ArrayList of Integers by choosing a pivot point and 
 * partitioning a list of smaller Integers to the left and a list 
 * of larger Integers to the right of the pivot point 
 * Inherits protected/public elements from MySort
 */
public class QuickSort extends MySort
{
	/**
	 * Populates member array list mNumbers with the elements from numbers  
	 * sets mElapsedTime and mNumComparisons to 0
	 * @param numbers is the ArrayList of Integers being sorted
	 */
	public QuickSort(ArrayList<Integer> numbers)
	{
		mNumbers = new ArrayList<Integer>(numbers.size());
		for(int i=0; i<numbers.size(); i++)
		{
			mNumbers.add(numbers.get(i));
		}
	}
	/**
	 * Returns the first index position of a list
	 * @param left is the first index position of a list
	 * @param right is the last index position of a list
	 * @return the first index position of a list
	 */
	public int getPivotIndex(int left, int right)
	{
		return left;
	}
	/**
	 * Partitions an ArrayList of Integers into a list 
	 * of Integers smaller than and greater than the pivot value 
	 * @param data is the ArrayList of Integers being sorted
	 * @param left is the left-most index position of the partitioned array
	 * @param right is the right-most index position of the partitioned array
	 * @return the new pivot position for recursive quickSort
	 */
	public int partition(ArrayList<Integer> data, int left, int right)
	{
		int pivot = data.get(getPivotIndex(left, right));
        while (true)
        {
        	while(data.get(left) < pivot)
            {
            	left++;
            	mNumComparisons++;
            }
        	//mNumComparisons++;
            while(data.get(right) > pivot)
            {
            	right--;
            	mNumComparisons++;
            }
            //mNumComparisons++;
            if (left < right)
            {
            	Collections.swap(data, left, right);
            }
            else
            {
            	return right;
            }
        }
	}
	
	/**
	 * Sorts by recursively partitioning the ArrayLst of Integers
	 * @param data is the ArrayList of Integers being sorted
	 * @param left is the left-most index position of the partitioned array
	 * @param right is the right-most index position of the partitioned array
	 */
	public void quickSort(ArrayList<Integer> data, int left, int right)
	{
        if (right - left >= 1)
        {
            if(left < right)
            {
                int pivot = partition(data, left, right);
                if(pivot > 1)
                {
                	quickSort(data, left, pivot - 1);
                }
                if(pivot + 1 < right)
                {
                	quickSort(data, pivot + 1, right);
                }
            }
        }
	}
	
	/**
	 * Overrides abstract askName method in MySort <p>
	 * Returns the name of the sorting algorithm: Quick Sort
	 * @return	sort method's name as a type String
	 */
	@Override
	public String askName()
	{
		return "Quick Sort";
	}
	
	/**
	 * Sorts the ArrayList 
	 * Increments the number of comparisons 
	 * Measures the elapsed time and catches StackOverflow error if needed
	 */
	@Override
	public void Sort() {
		Date before = new Date();
		try
		{
			quickSort(mNumbers, 0, mNumbers.size()-1);
		}
		catch(StackOverflowError error)
		{
			System.out.println("QuickSort Ran out of stack space");
		}
        Date after = new Date();
        mElapsedTime = after.getTime() - before.getTime();
	}

}
