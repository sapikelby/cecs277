/**
*
* CECS 277 Spring 2014.   Assignment 1
*
* @author Kelby Sapien 
*
*/

import java.util.Calendar;

/** 
 * Represents any person with basic personal information
 * Basic information includes: first name, last name, and birth date
 */
public class Person 
{
	/**
	 * Explicit parameters:
	 * @param first	person's first name
	 * @param last	person's last name
	 * @param birthDate	person's birth date
	 */
	public Person(String first, String last, Calendar birthDate)
	{
		fName = first; 
		lName = last; 
		bDate = birthDate; 
	}
	
	/**
	 * Gets person's first name
	 * @return this person's first name
	 */
	public String getFirstName()
	{
		return fName; 
	}
	
	/**
	 * Gets person's last name
	 * @return	this person's last name
	 */
	public String getLastName()
	{
		return lName; 
	}
	
	/**
	 * Gets person's birth date
	 * @return	this person's birth date
	 */
	public Calendar getBDate()
	{
		return bDate; 
	}
	
	/**
	 * Person's first name as a string
	 * Person's last name as string
	 * Person's birth date as a calendar date
	 */
	private String fName;
	private String lName; 
	private Calendar bDate;
}
