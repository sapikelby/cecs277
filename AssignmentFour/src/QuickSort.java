import java.util.ArrayList;
import java.util.Date;

public class QuickSort extends MySort{

	public QuickSort(ArrayList<Integer> numbers)
	{
		//super();
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
		mNumbers = quickSort(mNumbers);
	}
	
	public ArrayList<Integer> quickSort(ArrayList<Integer> unsortedList)
	{
		ArrayList<Integer> sortedList = new ArrayList<Integer>();
		//Date before = new Date();
		if(unsortedList.size() <= 1)
		{
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
		//Date after = new Date();
		//mElapsedTime = after.getTime() - before.getTime();
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
