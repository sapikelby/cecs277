/**
 *
* CECS 277 Spring 2014.   Assignment 1
*
 * @author Student one full name and last 4 digits of id
 * @author Student two full name and last 4 digits of id
 * @author Paul and last 4 digits of id
 * @author Kelby Sapien and last 4 digits of id
 */

import java.util.Calendar;


public class Person {
	
	public Person(String first, String last, Calendar birthDate)
	{
		fName = first; 
		lName = last; 
		bDate = birthDate; 
	}
	
	public String getFirstName()
	{
		return fName; 
	}
	
	public String getLastName()
	{
		return lName; 
	}
	
	public Calendar getBDate()
	{
		return bDate; 
	}
	

	private String fName; 
	private String lName; 
	private Calendar bDate;
}
