import java.util.ArrayList;
import java.util.Random;


public class QuickSortRandomized extends QuickSort {
	
	public QuickSortRandomized(ArrayList<Integer> numbers)
	{
		super(numbers);
	}
	
	public String askName()
	{
		return "Quick Sort (R)"; 
	}
	
	public void Sort()
	{
		//mNumbers = quickSort(mNumbers);
		quickSort(mNumbers);
	}
	
	public int getPivotIndex(int left, int right)
	{
		Random rand = new Random();
		//return left + rand.nextInt(right - left) + 1; 
		//int pivot = rand.nextInt(right - left); 
		int pivot = rand.nextInt(right);
		//System.out.println(pivot);
		return pivot;
	} 
}
