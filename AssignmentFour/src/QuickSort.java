import java.util.ArrayList;
import java.util.Date;

public class QuickSort extends MySort
{

	public QuickSort(ArrayList<Integer> numbers)
	{
		mElapsedTime = 0;
		mNumComparisons = 0;
		mNumbers = new ArrayList<Integer>(numbers.size());  
		for(int i=0; i<numbers.size(); i++)
		{
			mNumbers.add(numbers.get(i));
		}
	}
	
	public String askName()
	{
		return "Quick Sort"; 
	}
	
	public void Sort() 
	{
		Date before = new Date();
		try
		{
		mNumbers = quickSort(mNumbers);
		}
		catch(StackOverflowError err)
		{
			System.out.println(askName() + " ran out stack space");
		}
		Date after = new Date(); 
		mElapsedTime = after.getTime() - before.getTime();
	}
	
	public ArrayList<Integer> quickSort(ArrayList<Integer> unsortedList) 
	{
		
		ArrayList<Integer> sortedList = new ArrayList<Integer>();
		
		if(unsortedList.size() <= 1)
		{
			//return unsortedList;
			return unsortedList;
		}
		//picking pivot
		//FUTURE CHANGES:
		//Pick better pivot
		//int pivot = mNumbers.size()/2;
		//int pivotData = mNumbers.get(pivot);
		int pivot = getPivotIndex(0, unsortedList.size());
		int pivotData = unsortedList.get(pivot);

		unsortedList.remove(pivot);

		ArrayList<Integer> left = new ArrayList<Integer>();
		ArrayList<Integer> right = new ArrayList<Integer>();

		//Sort into numbers larger and smaller than the pivot
		for(int i = 0; i < unsortedList.size(); i++)
		{
			if(unsortedList.get(i) > pivotData)
			{
				right.add(unsortedList.get(i));
			}
			else
			{
				left.add(unsortedList.get(i));
			}
			mNumComparisons++;
		}
		
		//combine the lists
		sortedList = concatinate(quickSort(left), pivotData, quickSort(right));
	
		return sortedList;
	}

	public ArrayList<Integer> concatinate(ArrayList<Integer> left, int pivot, ArrayList<Integer> right)
	{
		ArrayList<Integer> sortedList = new ArrayList<Integer>();
		for(int i = 0; i < left.size(); i++)
		{
			sortedList.add(left.get(i));
		}
		sortedList.add(pivot);
		for(int i = 0; i < right.size(); i++)
		{
			sortedList.add(right.get(i));
		}
		return sortedList;
	}
	
	public int getPivotIndex(int left, int right)
	{
		return left;
	}
}
