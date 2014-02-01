/**
*
* CECS 277 Spring 2014.   Assignment 1
*
* @author Kelby Sapien 
*
*/

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ArrayList;
/**
 * Represents a person's checking account
 * Keeps track of account's balance and list of transactions
 */
public class CheckingAccount 
{
	/**
	 * balance of a new account is initialized to 0 
	 * @param person	the owner of the checking account
	 * 
	 */
	public CheckingAccount(Person person)
	{
		owner = person;
		balance = 0;
	}
	
	/**
	 * Gets owner of account
	 * @return the owner of the checking account
	 */
	public Person getOwner()
	{
		return owner; 
	}
	
	/**
	 * Adds deposit amount to balance
	 * Adds transaction to array list
	 * @param date 	date the deposit is made
	 * @param amount amount of the deposit
	 * 
	 */
	public void makeDeposit(Calendar date, double amount)
	{
		balance = balance + amount; 
		Transaction data = new Transaction(date, "Deposit", amount);
		List.add(data);
	}
	
	/**
	 * Deducts withdrawal amount from balance
	 * Adds transaction to array list
	 * @param date	date the withdrawal was made
	 * @param amount amount of the withdrawal
	 * 
	 */
	public void makeWithdrawal(Calendar date, double amount)
	{
		balance = balance - amount; 
		Transaction data = new Transaction(date, "Withdrawal", amount);
		List.add(data);
	}
	
	/**
	 * Returns account's balance
	 * @return the checking account's current balance
	 */
	public double getBalance()
	{
		return balance; 
	}
	
	/**
	 * overrides toString method and prints account's information
	 * returns empty string
	 */
	public String toString()
	{ 
		String date_format = "MM-dd-yyyy";
		String birthDate; 
		String transDate; 
		SimpleDateFormat sdf = new SimpleDateFormat(date_format);
		birthDate = sdf.format(getOwner().getBDate().getTime());
		
		
		System.out.println("Owner: " + getOwner().getFirstName() + " " +  getOwner().getLastName() + " " + 
				birthDate + ", Balance: " + String.format("%.2f", getBalance()));
		for(int i=0;i<List.size();i++){
			transDate = sdf.format(List.get(i).getDate().getTime());
			System.out.println(transDate + " " + List.get(i).getType() + " " +
					String.format("%.2f", List.get(i).getAmount()));
		}
		
		return "";
	}
	
	/**
	 * List contains all transactions 
	 */
	private ArrayList<Transaction> List = new ArrayList<Transaction>();
	/**
	 * balance is the checking account's balance
	 */
	private double balance;
	/**
	 * owner is the account holder
	 */
	private Person owner;
	
	/**
	 * Keeps track of each transaction's date, type and amount
	 */
	private class Transaction 
	{
		/**
		 * @param transDate is the date of the transaction
		 * @param transType	is the type of transaction
		 * @param amt is the amount transferred or deducted from account balance
		 */
		public Transaction(Calendar transDate, String transType, double amt)
		{
			date = transDate; 
			type = transType;
			amount = amt;
		}
		
		/**
		 * Gets the date of transaction
		 * @return the date of transaction
		 */
		public Calendar getDate()
		{
			return date;
		}
		
		/**
		 * Gets the type of transaction
		 * @return the transaction type
		 */
		public String getType()
		{
			return type;
		}
		
		/**
		 * Gets the amount of the transaction
		 * @return the transaction amount
		 */
		public double getAmount()
		{
			return amount;
		}
		
		/**
		 * transaction type: either a deposit or withdrawal
		 */
		private String type;
		/**
		 * date is the date of transaction
		 */
		private Calendar date;
		/**
		 * amount is the amount of the transaction
		 */
		private double amount;
	}
}
