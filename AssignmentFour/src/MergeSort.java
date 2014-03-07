import java.util.ArrayList;
import java.util.Date;


public class MergeSort extends MySort {

	public MergeSort(ArrayList<Integer> numbers)
	{
		//super();
		mNumbers = new ArrayList<Integer>(numbers.size()); 
		//System.out.println(mNumbers.size());
		
		for(int i=0; i<numbers.size() - 1; i++)
		{
			mNumbers.add(numbers.get(i));
		} 
		
		/*
		ArrayList<Integer> firstHalf = new ArrayList<Integer>(); 
		for(int i=0; i<numbers.size()/2 - 1; i++)
		{
			firstHalf.add(numbers.get(i));
		}
		
		ArrayList<Integer> secondHalf = new ArrayList<Integer>(); 
		for(int i=0; i<numbers.size() - firstHalf.size(); i++)
		{
			int next = firstHalf.size() + i; 
			secondHalf.add(numbers.get(next));
		}
		*/
	}
	
	public String askName()
	{
		return "Merge Sort"; 
	}
	
	public void Sort()
	{
		Date before = new Date();
		mNumbers = mergeSort(mNumbers);

		Date after = new Date(); 
		mElapsedTime = after.getTime() - before.getTime();
	}
	
	public ArrayList<Integer> mergeSort(ArrayList<Integer> unsortedList)
	{
		if(unsortedList.size() <= 1)
		{
			return unsortedList;
		}
		ArrayList<Integer> sortedList = new ArrayList<Integer>();

		ArrayList<Integer> left = new ArrayList<Integer>();
		ArrayList<Integer> right = new ArrayList<Integer>();
		int middle = unsortedList.size()/2;
		//Splits the array into unsortedList size lists of size one
		for(int i = 0; i < unsortedList.size(); i++)
		{
			if(i < middle)
			{
				left.add(unsortedList.get(i));
			}
			else
			{
				right.add(unsortedList.get(i));
			}
			
		}
		left = mergeSort(left); 
		right = mergeSort(right);
		//combines the lists
		sortedList = merge(left, right);
		return sortedList;
	}

	public ArrayList<Integer> merge(ArrayList<Integer> left, ArrayList<Integer> right)
	{
		ArrayList<Integer> mergedList = new ArrayList<Integer>();
		while(left.size() > 0 || right.size() > 0)
		{
			if(left.size() > 0 && right.size() > 0)
			{
				if(left.get(0) < right.get(0))
				{
					mergedList.add(left.get(0));
					left.remove(0);
					
				}
				else
				{
					mergedList.add(right.get(0));
					right.remove(0);
				}
				mNumComparisons++;
			}
			else if(left.size() > 0)
			{
				mergedList.add(left.get(0));
				left.remove(0);
			}
			else if(right.size() > 0)
			{
				mergedList.add(right.get(0));
				right.remove(0);
			}
			//mNumComparisons++;
		}
		return mergedList;
	}
	
}
