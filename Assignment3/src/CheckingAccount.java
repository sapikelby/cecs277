/**
*
* CECS 277 Spring 2014.   Assignment 3
*
* @author Kelby Sapien 3260
* @author Paul Kim 6816
*
*/

import java.util.Calendar;

// displayable, savings, transactions

/**
 * Represents a person's checking account
 * Keeps track of account's balance and list of transactions
 * Inherits public and protected elements from BankAccount
 */
public class CheckingAccount extends BankAccount
{
	/**
	 * calls Super class's constructor to create account
	 * CheckingAccount constructor
	 * @param person	the owner of the checking account
	 */
	public CheckingAccount(Person person)
	{
		super(person);
	}
	
	/**
	 * fee transaction is created and added to array list
	 * @param tDate		date the transaction was made
	 * @param amount	amount of the transaction
	 */
	public void chargeFee(Calendar tDate, double amount)
	{
		Transaction fee = new Transaction(amount, "Fee", tDate);
		addTransaction(fee);
		
	}
	
	/**
	 * returns account holder's name, birth date, and balance
	 */
	public String toString()
	{
		String acctInfo = "Checking Account. Owner: " + getOwner().toString() +  
		 ", Balance: "+ String.format("%.2f", getBalance()); 
		
		return acctInfo; 
	}

	/**
	 * gets the account type: checking
	 * overrides getType in BankAccount
	 * @return checking as the account type
	 */
	public String getType()
	{
		return "Checking"; 
	}
	
	/**
	 * compares two accounts and sorts them by balance
	 * @param acct	account to which checking account is being compared
	 * @returns  a negative integer, zero, or a positive integer as this account is 
	 * less than, equal to, or greater than the specified account. 
	 */
	@Override
	public int compareTo(BankAccount acct) {
		
		if(this.getBalance() > acct.getBalance())
			return 1; 
		else if(this.getBalance() < acct.getBalance())
			return -1; 
		else // equal account balances
			return 0; 
			
	
	}
	
}