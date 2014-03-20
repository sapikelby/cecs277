public class AssignmentFiveMain
{

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		doInteger();
		doString();
	}
	
	private static void doString()
	{
		String[] words = { "Algol", "BASIC", "FORTRAN", "COBOL", "RPG", "Lisp", "APL",
				           "Simula", "SNOBOL", "BCPL", "Pascal", "B",
				           "C", "C++", "FORTRAN", "Java", "JavaScript", "Python",
				           "Forth", "Haskell", "Prolog" };
		
		MySet<String> evenInts = new MySet<String>("Even words");
		MySet<String> oddInts = new MySet<String>("Odd words");
			
		for(int ii = 0; ii < 20; ++ii)
		{
			if(ii % 2 == 0) evenInts.add(words[ii]);
			else oddInts.add(words[ii]);
		}
		
		evenInts.list();
		oddInts.list();
		
		System.out.println("\nIs " + words[3] + " present in even list? " + evenInts.isPresent(words[3]));
		System.out.println("Is " + words[3] + " present in odd list? " + oddInts.isPresent(words[3]));
		
		try
		{
			evenInts.remove(words[19]);
		}
		catch(ItemNotFoundException exc)
		{
			System.out.println("\nCorrectly caught trying to remove " + exc.getMessage() + " from even list");
		}
		
		try
		{
			oddInts.remove(words[19]);
		}
		catch(ItemNotFoundException exc)
		{
			System.out.println("\nIncorrectly caught trying to remove " + exc.getMessage() + " from even list");
		}
		
		System.out.println("\nAfter removing " + words[19] + " from odd list, is " + words[19] + " in odd list? " + oddInts.isPresent(words[19]));
		
		MySet<String> union = oddInts.union(evenInts);
		union.list();
		
		MySet<String> emptySet = oddInts.intersect(evenInts);
		emptySet.list();
		
		MySet<String> subset = new MySet<String>("Subset of odd numbers");
		subset.add(words[15]);
		subset.add(words[17]);
		
		try
		{
			MySet<String> difference = oddInts.difference(subset);
			difference.list();
			
			emptySet = evenInts.difference(evenInts);
			emptySet.list();
			
			MySet<String> sameAsEven = evenInts.union(evenInts);
			sameAsEven.list();
			
			sameAsEven = evenInts.difference(new MySet<String>("Empty Set"));
		}
		catch(Exception exc)
		{
			System.out.println("Unexpected exception: " + exc.getMessage());
		}
	}		
	
	private static void doInteger()
	{
		MySet<Integer> evenInts = new MySet<Integer>("Even numbers");
		MySet<Integer> oddInts = new MySet<Integer>("Odd numbers");
			
		for(int ii = 0; ii < 20; ++ii)
		{
			if(ii % 2 == 0) evenInts.add(ii);
			else oddInts.add(ii);
		}
		
		evenInts.list();
		oddInts.list();
		
		System.out.println("\nIs 3 present in even list? " + evenInts.isPresent(3));
		System.out.println("Is 3 present in odd list? " + oddInts.isPresent(3));
		
		try
		{
			evenInts.remove(19);
		}
		catch(ItemNotFoundException exc)
		{
			System.out.println("\nCorrectly caught trying to remove " + exc.getMessage() + " from even list");
		}
		
		try
		{
			oddInts.remove(19);
		}
		catch(ItemNotFoundException exc)
		{
			System.out.println("\nIncorreclty caught trying to remove " + exc.getMessage() + " from even list");
		}
		
		System.out.println("\nAfter removing 19 from odd list, is 19 in odd list? " + oddInts.isPresent(19));
		
		MySet<Integer> union = oddInts.union(evenInts);
		union.list();
		
		MySet<Integer> emptySet = oddInts.intersect(evenInts);
		emptySet.list();
		
		MySet<Integer> subset = new MySet<Integer>("Subset of odd numbers");
		subset.add(15);
		subset.add(17);
		
		try
		{
			MySet<Integer> difference = oddInts.difference(subset);
			difference.list();
			
			emptySet = evenInts.difference(evenInts);
			emptySet.list();
			
			MySet<Integer> sameAsEven = evenInts.union(evenInts);
			sameAsEven.list();
			
			sameAsEven = evenInts.difference(new MySet<Integer>("Empty Set"));
		}
		catch(Exception exc)
		{
			System.out.println("Unexpected exception: " + exc.getMessage());
		}
	}		
}