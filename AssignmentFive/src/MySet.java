import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

public class MySet<T extends Comparable <T>>{
	/*
	public MySet<T>(String item) {
		// TODO Auto-generated constructor stub
	}
	*/
	public MySet(String name) 
	{
		listName = name;
		newSet = new ArrayList<T>();
		
		// TODO Auto-generated constructor stub
	}

	public void add(T word)
	{
		newSet.add(word);
	}
	
	public void list()
	{
		String list = "MySet " + getName() + " contains\n";
		for(int i=0; i<newSet.size(); i++)
		{
			list += newSet.get(i) + " ";
		}
		System.out.println(list + "\n");
		//return list;
	}

	public void remove(T string) throws ItemNotFoundException
	{
		if(newSet.contains(string))
		{
			newSet.remove(string);
		}
		else
		{
			throw new ItemNotFoundException(string.toString());
		}
		
	}
	
	public MySet<T> union(MySet<T> evenInts) 
	{
		MySet<T> temp = new MySet<T>("Union");
		
		temp.getList().addAll(this.newSet);
		
		int iterator = 0;
		while(!temp.getList().contains(evenInts.getList()) && iterator < evenInts.getList().size())
		{
			temp.getList().add(evenInts.getList().get(iterator));
			iterator++;
		}
		return temp;
	}
	
	public ArrayList<T> getList()
	{
		return newSet;
	}
	
	public MySet<T> difference(MySet<T> subset) 
	{
		MySet<T> temp = new MySet<T>("temp");
		
		temp.getList().addAll(this.newSet);
		
		MySet<T> difference = new MySet<T>("Difference");
		
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
		return difference;
	}

	public MySet<T> intersect(MySet<T> evenInts) 
	{
		MySet<T> temp = new MySet<T>("temp");
		
		temp.getList().addAll(this.newSet);
		
		MySet<T> intersect = new MySet<T>("Intersect");
		
		int iterator = 0;
		while(temp.getList().contains(evenInts.getList()) && iterator < evenInts.getList().size())
		{
			intersect.getList().add(evenInts.getList().get(iterator));
			iterator++;
		}
		return intersect;
	}

	public boolean isPresent(T string) {
		
		boolean present = newSet.contains(string) ? true : false;
		return present;
	}
	
	public String getName()
	{
		return listName;
	}

	private String listName; 
	private ArrayList<T> newSet;
	
}
