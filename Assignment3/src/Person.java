/**
*
* CECS 277 Spring 2014.   Assignment 3
*
* @author Kelby Sapien 3260
* @author Paul Kim 6816
*
*/
import java.util.Calendar;
import java.text.SimpleDateFormat; 


/** 
 * Represents a person with basic personal information
 * Basic information includes: first name, last name, and birth date
 */
public class Person 
{
	/**
	 * Person's constructor
	 * @param first	person's first name
	 * @param last	person's last name
	 * @param birthDate	person's birth date
	 */
	public Person(String first, String last, Calendar bDate)
	{
		_firstName = first; 
		_lastName = last; 
		_birthDate = bDate;
	}
	/**
	 * 
	 * @return person's first name
	 */
	public String getPerson()
	{
		return _firstName; 
	}
	/**
	 * 
	 * @return person's last name
	 */
	public String getLastName()
	{
		return _lastName; 
	}
	/**
	 * 
	 * @return person's birthdate
	 */
	public Calendar getBDate()
	{
		return _birthDate; 
	}
	/**
	 * @return first name, last name, and birth date
	 */
	public String toString()
	{
		String date_format = "MM-dd-yyyy";
		String birthDate; 
		SimpleDateFormat sdf = new SimpleDateFormat(date_format);
		birthDate = sdf.format(_birthDate.getTime());
		return _firstName + " " +  _lastName + " " + birthDate; 
	}
	
	/**
	 * Person's first name 
	 * Person's last name 
	 * Person's birth date 
	 */
	private String _firstName;
	private String _lastName;
	private Calendar _birthDate;
	
}
