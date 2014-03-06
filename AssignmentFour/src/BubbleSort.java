import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class BubbleSort extends MySort {

	public BubbleSort(ArrayList<Integer> numbers) 
	{
		super();
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

	public void Sort() {
		Date before = new Date();
		
		boolean swap = true;
		int temp = 0; 
		int j = 0; // used to decrement arrayList traversal
		while (swap) {
			swap = false;
			j++;
			for (int i = 0; i < (mNumbers.size() - 1) - j; i++) 
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
		mElapsedTime = after.getTime() - before.getTime();
	}
}
