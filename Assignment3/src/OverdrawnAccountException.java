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
 * Exceptions class for overdrawing exceptions
 * Extends the Exception's class
 */
public class OverdrawnAccountException extends Exception
{	
	/**
	 * constructor, calls superclass's constructor 
	 * increments the count by 1
	 * @param message message printed when exception is thrown
	 */
	public OverdrawnAccountException(String message)
	{
		super(message);
		count++;
	}
	
	/**
	 * gets the number of times an account overdraw occurs
	 * @return number of times this exception is thrown
	 */
	public static int getCount()
	{
		return count; 
	}
	
	/**
	 * calls superclass getMessage function
	 * overrides getMessage
	 * @return the exception as a string
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
