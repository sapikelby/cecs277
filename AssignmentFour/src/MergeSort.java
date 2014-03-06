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
		mergeSort(mNumbers);

		Date after = new Date(); 
		mElapsedTime = after.getTime() - before.getTime();
	}
	
	public ArrayList<Integer> mergeSort(ArrayList<Integer> a) 
	{
		if (a.size() <= 1) 
		{
			return a;
		}
		ArrayList<Integer> firstHalf = new ArrayList<>();
		ArrayList<Integer> secondHalf = new ArrayList<>();
		
		for (int i = 0; i < a.size() / 2; i++) {
			firstHalf.add(a.get(i));
		}
		for (int i = a.size() / 2; i < a.size(); i++) {
			secondHalf.add(a.get(i));
		}
		
		return merge(mergeSort(firstHalf), mergeSort(secondHalf));
	}

	public ArrayList<Integer> merge(ArrayList<Integer> l1, ArrayList<Integer> l2) 
	{
		if (l1.size() == 0) 
		{
			return l2;
		}
		if (l2.size() == 0) 
		{
			return l1;
		}
		
		
		ArrayList<Integer> result = new ArrayList<>();
		int nextElement;
		mNumComparisons++;
		if (l1.get(0) > l2.get(0)) {
			nextElement = l2.get(0);
			l2.remove(0);
		} else {
			nextElement = l1.get(0);
			l1.remove(0);
		}
		result.add(nextElement);
		result.addAll(merge(l1, l2));
		return result;
	}
	
}
