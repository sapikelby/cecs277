/**
*
* CECS 277 Spring 2014.   Assignment 2
*
* @author Kelby Sapien 3260
* @author Paul Kim 6816
*
*/
import java.text.SimpleDateFormat;
import java.util.Calendar;
/**
 * Transaction object has an amount, type and date.
 */
public class Transaction
{
	/**
	 * Creates a Transaction object with amount, type and date information
	 * @param amt	the amount of the transaction
	 * @param type	the type of the transaction
	 * @param date	the date the transaction occurred
	 */
	public Transaction(double amt, String type, Calendar date)
	{
		_amount = amt; 
		_type = type; 
		_transDate = date; 
	}
	/**
	 * Makes a copy of the Transaction
	 * @param other is the original Transaction
	 */
	public Transaction(Transaction other)
	{
		_amount = other._amount;
		_type = other._type; 
		_transDate = other._transDate; 
		
	}
	/**
	 * returns double Transaction amount
	 * @return Transaction amount as double
	 */
	public double getAmount()
	{
		return _amount; 
	}
	
	/**
	 * returns String Transaction type
	 * @return Transaction type as String
	 */
	public String getType()
	{
		return _type; 
	}
	
	/**
	 * returns Calendar Transaction date
	 * @return Transaction date as Calendar
	 */
	public Calendar getDate()
	{
		return _transDate; 
	}
	
	/**
	 * Overrides toString method
	 * @return the date, type, and amount of transaction
	 */
	public String toString()
	{
		String date_format = "MM-dd-yyyy";
		String tDate; 
		SimpleDateFormat sdf = new SimpleDateFormat(date_format);
		tDate = sdf.format(_transDate.getTime());
		String transInfo = tDate + " " + _type + " " + String.format("%.2f", _amount); 
		return transInfo; 
	}
	
	/**
	 * _type is the transaction type
	 * _amount is the amount of the transaction
	 * _transDate is the date of transaction
	 */
	private String _type; 
	private double _amount; 
	private Calendar _transDate; 
}
