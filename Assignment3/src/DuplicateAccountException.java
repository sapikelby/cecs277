/**
*
* CECS 277 Spring 2014.   Assignment 3
*
* @author Kelby Sapien 3260
* @author Paul Kim 6816
*
*/
import java.lang.Exception;

/**
 * Exceptions class for creating duplicate accounts
 * Extends the Exception's class
 */
public class DuplicateAccountException extends Exception {
	
	/**
	 * constructor, calls superclass's constructor
	 * increments count by 1
	 * @param message
	 */
	public DuplicateAccountException(String message)
	{
		super(message);
		count++;
	}
	
	/**
	 * gets the number of times a duplicate account attempts to be added
	 * @return number of times this exception is thrown
	 */
	public static int getCount()
	{
		return count; 
	}
	
	/**
	 * calls superclass getMessage function
	 * overrides getMessage
	 * @return exception as a string
	 */
	public String getMessage()
	{
		return super.getMessage();
	}

	/**
	 * static variable to keep track of exceptions thrown
	 */
	private static int count = 0; 
	
}
