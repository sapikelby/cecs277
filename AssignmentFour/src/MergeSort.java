import java.util.ArrayList;
import java.util.Date;


public class MergeSort extends MySort {

	public MergeSort(ArrayList<Integer> numbers)
	{
		mElapsedTime = 0;
		mNumComparisons = 0;
		mNumbers = new ArrayList<Integer>(numbers.size()); 
		//System.out.println(mNumbers.size());
		
		for(int i=0; i<numbers.size(); i++)
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
		Sort(mNumbers);

		Date after = new Date(); 
		mElapsedTime = after.getTime() - before.getTime();
	}
	
	private void Sort(ArrayList<Integer> numbers)
	{
		if (numbers.size() <= 1) 
		{
			return;
		}
		ArrayList<Integer> firstHalf = new ArrayList<>();
		ArrayList<Integer> secondHalf = new ArrayList<>();
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
