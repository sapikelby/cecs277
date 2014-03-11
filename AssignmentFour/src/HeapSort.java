import java.util.ArrayList;
import java.util.Date;
import java.util.Collections;

public class HeapSort extends MySort{

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
	
	public String askName()
	{
		return "Heap Sort"; 
	}
	
	public void Sort() 
	{
		mNumbers = heapSort(mNumbers);
	}
	

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
	
	public void heapify(ArrayList<Integer> unsortedList, int count)
	{
		int start = count/2 - 1;
		while(start >= 0)
		{
			siftDown(unsortedList, start, count - 1);
			start -= 1;
		}
	}
	
	public void siftDown(ArrayList<Integer> unsortedList, int start, int end)
	{
		int root = start;
		while(root*2+1 <= end)
		{
			int child = root*2+1;
			int swap = root;
			mNumComparisons += 2; // two comparisons being done below
			if(unsortedList.get(swap) < unsortedList.get(child))
			{
				//mNumComparisons++;
				swap = child;
			}
			
			if(child+1 <= end && unsortedList.get(swap) < unsortedList.get(child+1))
			{
				//mNumComparisons++;
				swap = child+1;
			}
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
	
	public void swap(ArrayList<Integer> unsortedList, int swapOne, int swapTwo)
	{
		int holder = unsortedList.get(swapOne);
		unsortedList.set(swapOne, unsortedList.get(swapTwo));
		unsortedList.set(swapTwo, holder);
	}
}	
