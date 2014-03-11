import java.util.ArrayList;
import java.util.Random;

// heapsort and mergesort
/**
 *	Best case:
 *	Worst case:
 *	Avg. case:
 */
public class QuickSortRandomized extends QuickSort {
	
	public QuickSortRandomized(ArrayList<Integer> numbers)
	{
		super(numbers);
	}
	
	public String askName()
	{
		return "Quick Sort (R)"; 
	}

	public int getPivotIndex(int left, int right)
	{
		Random rand = new Random();
		return left + rand.nextInt(right - left + 1);
	} 
}
