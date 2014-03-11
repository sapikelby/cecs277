import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class BubbleSort extends MySort {

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

	public String askName() 
	{
		return "Bubble Sort";
	}

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
	
	public void printList()
	{
		for(int i=0; i < mNumbers.size(); i++)
		{
			System.out.println(mNumbers.get(i));
		}
	}
}
