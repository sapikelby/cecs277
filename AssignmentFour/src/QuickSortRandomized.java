import java.util.ArrayList;


public class QuickSortRandomized extends MySort {
	
	public QuickSortRandomized(ArrayList<Integer> numbers)
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
		return "Quick Sort (R)"; 
	}
	
	public void Sort()
	{
		
	}
}
