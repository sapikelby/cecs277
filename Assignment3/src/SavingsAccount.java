/**
*
* CECS 277 Spring 2014.   Assignment 2
*
* @author Kelby Sapien 3260
* @author Paul Kim 6816
*
*/
import java.util.Calendar;

/**
 * Represents a person's savings account
 * Keeps track of account's balance and list of transactions
 * Inherits public and protected elements from BankAccount
 */
public class SavingsAccount extends BankAccount
{
	/**
	 * calls Super class's constructor to create account
	 * @param person is the account holder
	 */
	public SavingsAccount(Person person)
	{
		super(person);
	}
	
	/**
	 * creates interest transaction and adds transaction to array list
	 * @param tDate date interest was paid
	 * @param amount amount of transaction
	 */
	public void postInterest(Calendar tDate, double amount)
	{
		Transaction trans = new Transaction(amount, "Interest", tDate);
		addTransaction(trans);
	}
	
	/**
	 * calls getBalance from Super class
	 * gets account's balance
	 * @return	account balance
	 */
	public double getAccountBalance()
	{
		double balance = getBalance();
		return balance;
	}
	
	/**
	 * prints owner's info and their account balance
	 * @return owner's info and their account information
	 */
	public String toString()
	{
		String acctInfo = "Savings Account. Owner: " + getOwner().toString() +  
		 ", Balance: "+ String.format("%.2f", getBalance()); 
		
		return acctInfo; 
	}
	
	/**
	 * gets account type: savings
	 * overrides getType in BankAccount
	 * @return savings as the account type
	 */
	public String getType()
	{
		return "Savings"; 
	}
	
	@Override
	/**
	 * compares two accounts and sorts them by balance
	 * @param acct	account to which savings account is being compared
	 * @returns  a negative integer, zero, or a positive integer as this account is 
	 * less than, equal to, or greater than the specified account. 
	 */
	public int compareTo(BankAccount acct) {
		if(this.getBalance() > acct.getBalance())
			return 1; 
		else if(this.getBalance() < acct.getBalance())
			return -1; 
		else
			return 0;
	}
}
