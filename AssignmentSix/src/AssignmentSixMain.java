import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class AssignmentSixMain 
{
	public static void main(String[] args) throws FileNotFoundException 
	{
		Scanner sc = new Scanner(new File(args[0]));
		ExpressionStore expStore = new ExpressionStore();
		List<String> names = new ArrayList<String>();
		Random generator = new Random();
				
		while(sc.hasNext())
		{
			String expName = sc.next();
			String expression = sc.next();
			
			expStore.store(expName, expression);
			names.add(expName);
		}
		
		System.out.println("\nUnsorted");
		for(String name : names)
		{
			System.out.println(name + ":" + expStore.getExpression(name) + " = " + expStore.getValue(name));
		}
		
		int ind = generator.nextInt(names.size());
		String remName = names.get(ind);
		names.remove(ind);
		expStore.remove(remName);
		System.out.println("\nRemoved: " + remName);
		
		System.out.println("\nSorted via iterator");
		String current = expStore.getLeast();
		
		while(current != null)
		{
			System.out.println(current + ":" + expStore.getExpression(current) + " = " + expStore.getValue(current)); 
			current = expStore.getNext(current);
		}
	}
}