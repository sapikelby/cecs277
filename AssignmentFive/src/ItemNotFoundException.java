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
public class ItemNotFoundException extends Exception
{	
	/**
	 * constructor, calls superclass's constructor 
	 * increments the count by 1
	 * @param message message printed when exception is thrown
	 */
	public ItemNotFoundException(String message)
	{
		super(message);
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

}