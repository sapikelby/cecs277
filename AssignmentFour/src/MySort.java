import java.util.ArrayList;

public abstract class MySort 
{
	public enum Type { BUBBLE_SORT, HEAP_SORT, QUICK_SORT, QUICK_SORT_RANDOMIZED, MERGE_SORT };
	
	public static MySort createSortFactory(MySort.Type type, ArrayList<Integer> numbers) throws Exception
	{
		MySort sorter;
		
		switch(type)
		{
			case BUBBLE_SORT:
				sorter = new BubbleSort(numbers);
				break;
				
			case HEAP_SORT:
				sorter = new HeapSort(numbers);
				break;
				
			case QUICK_SORT:
				sorter = new QuickSort(numbers);
				break;
				
			case QUICK_SORT_RANDOMIZED:
				sorter = new QuickSortRandomized(numbers);
				break;
				
			case MERGE_SORT:
				sorter = new MergeSort(numbers);
				break;
				
			default:
				throw new Exception("Unknow sort type: " + type);
		}
		
		return sorter;	
	}
	
	public final ArrayList<Integer> getNumbers()
	{
		ArrayList<Integer> result = new ArrayList<Integer>();
		for(Integer ii : mNumbers) result.add(new Integer(ii));
		
		return result;
	}
	
	public final void displayResults() 
	{
		System.out.print(askName() + ":\tElapsed Time:");
		System.out.format("%,10d", mElapsedTime);
		System.out.print(",\tComparisons: ");
		System.out.format("%,12d%n", mNumComparisons);
	}
	
	public abstract String askName();
	public abstract void Sort();
	
	protected ArrayList<Integer> mNumbers;
	protected long mElapsedTime;
	protected long mNumComparisons;
		
}
