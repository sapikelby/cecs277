
public class StackOverFlowException extends Exception{
	/**
	 * constructor, calls superclass's constructor 
	 * increments the count by 1
	 * @param message message printed when exception is thrown
	 */
	public StackOverFlowException(String message)
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

