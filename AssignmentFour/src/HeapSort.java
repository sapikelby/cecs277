import java.util.ArrayList;


public class HeapSort extends MySort{

	public HeapSort(ArrayList<Integer> numbers)
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
		return "Heap Sort"; 
	}
	
	public void Sort()
	{
		
	}
}
