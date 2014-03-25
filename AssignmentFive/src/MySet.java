/**
*
* CECS 277 Spring 2014.   Assignment 5
*
* @author Kelby Sapien 3260
* @author Paul Kim 6816
*
*/
import java.util.ArrayList;
/**
 * Provides basic utility functions for sets
 * Adds items to set, removes items from set, and prints sets
 * Finds union, intersect, and difference of sets
 */
public class MySet<T extends Comparable <T>>
{
	/**
	 * parameterized constructor
	 * @param name is the name of the set
	 */
	public MySet(String name) 
	{
		listName = name;
		newSet = new ArrayList<T>();

	}

	/**
	 * adds item of type T to set
	 * @param item is the item to be added to the set
	 */
	public void add(T item)
	{
		newSet.add(item);
	}

	/**
	 * Acts like a ToString for the set
	 * prints set
	 */
	public void list()
	{
		String list = "MySet " + getName() + " contains\n";
		for(int i=0; i<newSet.size(); i++)
		{
			list += newSet.get(i) + " ";
		}
		System.out.println(list + "\n");
		
	}
	
	/**
	 * Removes item from set
	 * @param item is the item to be removed
	 * @throws ItemNotFoundException if item to be removed is not part of set
	 */
	public void remove(T item) throws ItemNotFoundException
	{
		if(newSet.contains(item))
		{
			newSet.remove(item);
		}
		else
		{
			throw new ItemNotFoundException(item.toString());
		}

	}

	/**
	 * takes the union of two sets
	 * @param evenInts other set needed to take the union of the two sets
	 * @return set representing the union between the two sets
	 */
	public MySet<T> union(MySet<T> evenInts) 
	{
		String setName = "Union(" + getName() + "," + evenInts.getName() + ")";
		MySet<T> temp = new MySet<T>(setName);

		temp.getList().addAll(this.newSet);

		int iterator = 0;
		while(!temp.getList().contains(evenInts.getList()) && iterator < evenInts.getList().size())
		{
			if(!temp.getList().contains(evenInts.getList().get(iterator)))
			{
				temp.getList().add(evenInts.getList().get(iterator));
			}
				
			iterator++;
		}
		return temp;
	}

	/**
	 * allows us to use all the functionality of an ArrayList
	 * @return the member set of this class
	 */
	public ArrayList<T> getList()
	{
		return newSet;
	}
	
	/**
	 * finds difference between two sets
	 * @param subset is the second set being compared
	 * @return a set representing the difference between the two sets
	 */
	public MySet<T> difference(MySet<T> subset) 
	{
		String setName = "Difference(" + getName() + "," + subset.getName() + ")";
		MySet<T> temp = new MySet<T>(setName);

		temp.getList().addAll(this.newSet);

		int iterator = 0;
		while(iterator < subset.getList().size())
		{
			T itemRemove = subset.getList().get(iterator);
			if(temp.getList().contains(subset.getList().get(iterator)))
			{
				temp.getList().remove(itemRemove); 
			}

			iterator++;
		}
		return temp;
	}

	/**
	 * finds the intersection of two sets
	 * @param evenInts is the second set being compared
	 * @return a set representing the intersect of the two sets
	 */
	public MySet<T> intersect(MySet<T> evenInts) 
	{
		String setName = "Intersect(" + getName() + "," + evenInts.getName() + ")";
		MySet<T> temp = new MySet<T>("temp");

		temp.getList().addAll(this.newSet);

		MySet<T> intersect = new MySet<T>(setName);

		int iterator = 0;
		while(temp.getList().contains(evenInts.getList()) && iterator < evenInts.getList().size())
		{
			intersect.getList().remove(evenInts.getList().get(iterator));
			iterator++;
		}
		return intersect;
	}

	/**
	 * @param item is the item checked in the set
	 * @return boolean(true) if item is part of set
	 */
	public boolean isPresent(T item) {

		boolean present = newSet.contains(item) ? true : false;
		return present;
	}

	/**
	 * gets the name of the set
	 * @return name of the set
	 */
	public String getName()
	{
		return listName;
	}

	/**
	 * listName is the name of the set
	 * newSet is the ArrayList representation of the set
	 */
	private String listName; 
	private ArrayList<T> newSet;

}
