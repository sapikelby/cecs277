import java.util.Date;
import java.util.ArrayList;
import java.util.Collections;


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
	
	public int getPivotIndex(int left, int right)
	{
		return left;
	}
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
        	mNumComparisons++;

            while(data.get(right) > pivot)
            {
            	right--;
            	mNumComparisons++;
            }
            mNumComparisons++;
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
	
	@Override
	public String askName()
	{
		return "Quick Sort";
	}

	@Override
	public void Sort() {
		Date before = new Date();
		try
		{
			quickSort(mNumbers, 0, mNumbers.size()-1);
		}
		catch(StackOverflowError error)
		{
			System.out.println(askName() + " ran out of stack space");
		}
		
        Date after = new Date();
        mElapsedTime = after.getTime() - before.getTime();
	}

}
