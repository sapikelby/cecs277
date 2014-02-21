/**
*
* CECS 277 Spring 2014.   Assignment 3
*
* @author Kelby Sapien 3260
* @author Paul Kim 6816
*
*/

import java.util.ArrayList;
import java.util.Calendar;
import java.lang.Comparable;


/**
 * Represents a person's bank account
 * Super class to checking and savings account
 * With functionalities such as withdrawing, depositing, getting balance, and
 * printing/adding transactions  
 */
public abstract class BankAccount implements Comparable<BankAccount>
{
		/**
		 * BankAccount constructor 
		 * creates account for person and array list to track their transactions
		 * @param owner of the account
		 */
	   	public BankAccount(Person owner)
	   	{
	   		_owner = owner; 
	   		_transactions = new ArrayList<Transaction>();
	   	}
	   	
	   	/**
	   	 * copy constructor
	   	 * @param account to be copied by copy constructor
	   	 */
	 	public BankAccount(BankAccount account)
	 	{
	 		_owner = account._owner;
	 		ArrayList<BankAccount> bankAcct = new ArrayList<BankAccount>();
	 		for(BankAccount trans: bankAcct)
	 		{
	 			bankAcct.add(trans);
	 		}
	 	}
	 	
	   	/**
	   	 * creates new withdrawal transaction that gets added to array list
	   	 * @param tDate date the withdrawal is made
	   	 * @param amount amount of the withdrawal
	   	 * @throws OverdrawnAccountException when the amount withdrawn exceeds the account's balance
	   	 */	 	
	   	public void makeWithdrawal(Calendar tDate, double amount) throws OverdrawnAccountException
		{
	   		
	   		if(amount > getBalance())
	   		{
	   			double shortBalance = getBalance() - amount;
	   			throw new OverdrawnAccountException("Owner: " + getOwner().toString() + ", Type: " + getType() + 
	   			 ", Balance: " + getBalance() + ", Withdrawal: " +  amount + ", Short: " + shortBalance);
	   		}
	   		else
	   		{
	   			Transaction newTrans = new Transaction(amount, "Withdrawal", tDate); 
	   			_transactions.add(newTrans);
	   		}
		}
	   	
	   	/**
	   	 * creates deposit transaction that gets added to array list
	   	 * @param tDate date of deposit
	   	 * @param amount amount of the deposit
	   	 */
	   	public void makeDeposit(Calendar tDate, double amount)
	   	{
	   		Transaction newTrans = new Transaction(amount, "Deposit", tDate);
	   		_transactions.add(newTrans);
	   	}
	   	
	   	/**
	   	 * adds transaction to array list
	   	 * @param trans transaction to be added to the array list
	   	 */
	   	public void addTransaction(Transaction trans)
	   	{
	   		_transactions.add(trans);
	   	}
	   	
	   	/**
	   	 * obtains account balance by adding/subtracting transactions
	   	 * @return the balance for each account
	   	 */
	   	public double getBalance()
	   	{
	   		double balance = 0; 
	   		for(int i=0; i<_transactions.size(); i++)
	   		{
	   			if(_transactions.get(i).getType() == "Deposit" || 
	   			 _transactions.get(i).getType() == "Interest")
	   			{
	   				balance = balance + _transactions.get(i).getAmount();
	   			}
	   			else 
	   			{
	   				balance = balance - _transactions.get(i).getAmount();
	   			}
	   			
	   		}
	   		
	   		return balance; 
	   	}
	   	
	   	/**
	   	 * 
	   	 * @return the number of transactions for each account
	   	 */
	   	public int getNumberTrans()
	   	{
	   		return _transactions.size();
	   	}
	   	
	   	/**
	   	 * prints all of the transactions belonging to an account
	   	 * @return the total number of transactions as a long string
	   	 */
	   	public String printTransactions()
	   	{
	   		String trans = ""; 
	   		
	   		for(int i=0; i<_transactions.size(); i++)
	   		{
	   			trans = trans +  _transactions.get(i).toString() + "\n"; 
	   		}
	   		
	   		return trans; 
	   	}
	   	
	   	/**
	   	 * gets the owner of the account
	   	 * @return the owner of the account
	   	 */
	   	public Person getOwner()
	   	{
	   		return _owner; 
	   	}
	   	
	   	/**
	   	 * abstract method to be implemented in the savings and checking accounts
	   	 * @return account type
	   	 */
	   	public abstract String getType();
	   	
	   	
	   	
	   	/**
	   	 * _owner is the account holder
	   	 * _transactions is an array list that holds transactions for each account
	   	 */
		private Person _owner;
		private ArrayList<Transaction> _transactions;
}
