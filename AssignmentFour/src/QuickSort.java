import java.util.ArrayList;


public class QuickSort extends MySort{

	public QuickSort(ArrayList<Integer> numbers)
	{
		super();
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
		
	}
}
