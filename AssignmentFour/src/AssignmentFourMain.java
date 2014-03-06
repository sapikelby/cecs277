import java.util.Collections;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import javax.swing.JOptionPane;


public class AssignmentFourMain 
{
	public static boolean isSorted(ArrayList<Integer> numbers)
	{
		for(int ii = 0; ii < numbers.size() - 2; ++ii)
		{
			if(numbers.get(ii) > numbers.get(ii + 1)) return false;
		}
		return true;
	}
	
	public static void main(String[] args) throws Exception
	{
		String[] names = { "Random", "Increasing", "Decreasing" };
		Random generator = new Random();
		
		int N = Integer.parseInt(JOptionPane.showInputDialog("Enter array size (0 = quit)"));
		if (N == 0) return;
		
		for(int ii = 0; ii < 3; ++ii)
		{
			System.out.println("\n" + names[ii] + " List of " + N + " integers.");
			System.out.println("==========================================================================");
			ArrayList<Integer> numbers = new ArrayList<Integer>(N);
			ArrayList<Integer> copyNumbers = new ArrayList<Integer>(N);
		
			for(int jj = 0; jj < N; ++jj)
			{
				int n;
				if(ii == 0)	n = generator.nextInt();
				else if (ii == 1) n = jj;
				else n = N - jj;
				
				numbers.add(n);
				copyNumbers.add(n);
			}
			
			// factory is a static method
			ArrayList<MySort> sorters = new ArrayList<MySort>();
			sorters.add(MySort.createSortFactory(MySort.Type.BUBBLE_SORT, numbers));
			sorters.add(MySort.createSortFactory(MySort.Type.HEAP_SORT, numbers));
			sorters.add(MySort.createSortFactory(MySort.Type.QUICK_SORT, numbers));
			
			// have Quick sort randomized inherit from quick sort and write the new method
			sorters.add(MySort.createSortFactory(MySort.Type.QUICK_SORT_RANDOMIZED, numbers));
			sorters.add(MySort.createSortFactory(MySort.Type.MERGE_SORT, numbers));
			
			numbers.clear();
			
			for(MySort sorter : sorters)
			{
				sorter.Sort();
				sorter.displayResults();
				if(!isSorted(sorter.getNumbers()))
				{
					System.out.println("ARRAY NOT CORRECTLY SORTED");
				}
			}
			
			Date before = new Date();
			Collections.sort(copyNumbers);
			Date after = new Date();
			long elapsed = after.getTime() - before.getTime();
			System.out.println("Collection.sort: " + elapsed);
			
		} // end for next set of integers
	} // end main()
}
