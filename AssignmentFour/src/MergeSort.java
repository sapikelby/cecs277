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
 * Divide and conquer sorting algorithm
 * Splits array list into left and right halves and recursively
 * sorts resulting collections or halves. The two sorted halves are then
 * combined or merged by comparing keys of each list
 */
public class MergeSort extends MySort {

	/**
	 * Populates member array list mNumbers with the elements from numbers 
	 * sets mElapsedTime and mNumComparisons to 0
	 * @param numbers is the ArrayList of Integers being sorted
	 */
	public MergeSort(ArrayList<Integer> numbers)
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
	 * Returns the name of the sorting algorithm: Merge Sort
	 * @return	sort method's name as a type String
	 */
	public String askName()
	{
		return "Merge Sort"; 
	}
	
	/**
	 * Calls merge sort algorithm to sort array list
	 * Calculates mElapsedTime for algorithm
	 */
	public void Sort()
	{
		Date before = new Date();
		Sort(mNumbers);

		Date after = new Date(); 
		mElapsedTime = after.getTime() - before.getTime();
	}
	/**
	 * Creates 2 array list to divide the problem into subset lists
	 * Sorts each half and then merges them by comparing keys in each half
	 * @param numbers is array list being sorted
	 */
	private void Sort(ArrayList<Integer> numbers)
	{
		if (numbers.size() <= 1) 
		{
			return;
		}
		ArrayList<Integer> firstHalf = new ArrayList<Integer>();
		ArrayList<Integer> secondHalf = new ArrayList<Integer>();
		int half = numbers.size() / 2;
		for (int i = 0; i < half; i++)
		{
			firstHalf.add(numbers.get(i));
		}
		for(int i = half; i < numbers.size(); i++)
		{
			secondHalf.add(numbers.get(i));
		}
		Sort(firstHalf);
		Sort(secondHalf);
		merge(firstHalf, secondHalf, numbers);
	}
	/**
	 * Merges two list by comparing values in each list
	 * The smaller of the two compared values is added to a sorted list
	 * Increments mNumComparisons as key comparisons are made
	 * @param first is the first array list being merged
	 * @param second is the second array list being merged
	 * @param numbers is the array list that holds sorted values
	 */
	private void merge(ArrayList<Integer> first, ArrayList<Integer> second, ArrayList<Integer> numbers)
	{
		int iFirst = 0;
		int iSecond = 0;
		int count = 0;
		
		while(iFirst < first.size() && iSecond < second.size())
		{
			mNumComparisons++;
			if (first.get(iFirst) < second.get(iSecond))
			{
				numbers.set(count, first.get(iFirst));
				iFirst++;
			}
			else
			{
				numbers.set(count, second.get(iSecond));
				iSecond++;
			}
			count++;
		}
		while (iFirst < first.size())
		{
			numbers.set(count, first.get(iFirst));
			iFirst++; 
			count++;
		}
		while (iSecond < second.size())
		{
			numbers.set(count, second.get(iSecond));
			iSecond++; 
			count++;
		}
	}
	
}
