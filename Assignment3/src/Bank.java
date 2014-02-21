/**
*
* CECS 277 Spring 2014.   Assignment 3
*
* @author Kelby Sapien 3260
* @author Paul Kim 6816
*
*/

import java.util.ArrayList;
import java.util.Collections;

/**
 *	Bank object has a name, a manager, and array list to keep track of all accounts
 */
public class Bank implements Displayable
{
	
	/**
	 * Bank constructor - creates bank with name, manager, and 
	 * array list to hold accounts
	 * @param name		bank name
	 * @param manager	bank's manager name
	 */
	public Bank(String name, Person manager)
	{
		_name = name; 
		_manager = manager; 
		_accounts = new ArrayList<BankAccount>();
	}
	
	/**
	 * copy constructor 
	 * @param other bank object to be copied
	 */
	public Bank(Bank other)
	{
		_name = other._name; 
		_manager = other._manager;
		ArrayList<BankAccount> _accounts = other._accounts; 
	}
	
	/**
	 * Displays accounts and transactions belonging to each account in sorted/unsorted fashion
	 * @param sorted	boolean used to display accounts either sorted or unsorted
	 * @return returns empty string
	 */
	public String getDisplay(boolean sorted)
	{
		ArrayList<BankAccount> temp = new ArrayList<BankAccount>();
		String bankInformation = this.toString(); 
		String trans = ""; 
		
		// make copy of bank accounts
		for(int i=0; i<_accounts.size(); i++)
		{
			temp.add(_accounts.get(i));
		}
		
		if(sorted)
		{
			
			System.out.println("Sorted");
			System.out.println(bankInformation);
			System.out.println("Accounts");
			
			Collections.sort(temp);

			for (BankAccount account : temp)
			{
				System.out.println(account);
				trans = account.printTransactions();
				System.out.println(trans);
			}
		}
			
		else 
		{ 
			System.out.println("Unsorted");
			System.out.println(bankInformation);
			System.out.println("Accounts");
			
			for (BankAccount account : _accounts)
			{
				System.out.println(account);
				trans = account.printTransactions();
				System.out.println(trans);
			}
		}
		return ""; 
	}
	
	/**
	 * @return	returns the bank's info and manager's info
	 */
	public String toString()
	{
		String bankInfo = "Bank Name: " + _name + "\n" + 
		 "Manager: " + _manager.toString(); 
		
		return bankInfo;
	}
	/**
	 * 
	 * @return the bank name
	 */
	public String getBankName()
	{
		return _name; 
	}
	
	/**
	 * 
	 * @return the bank's manager
	 */
	public Person getManager()
	{
		return _manager; 
	}
	
	/**
	 * adds account to BankAccount array list if account by same type and same owner does not exist
	 * @param account account to be added to the array list
	 * @throws DuplicateAccountException if account of same type and same owner exists
	 */
	public void addAccount(BankAccount account) throws DuplicateAccountException
	{
		// add first account if empty array list
		if(_accounts.size() == 0)  
		{
			_accounts.add(account); 
		}
		//  check for same name, birth date, and account type
		else
		{
			for(int i =0; i<_accounts.size(); i++)
			{
				if(_accounts.get(i).getOwner().toString().equals(account.getOwner().toString()) && 
						_accounts.get(i).getType().equals(account.getType()))
				{
					throw new DuplicateAccountException("Owner: " + account.getOwner() + 
					 ", Type: " +  account.getType()); 
				}
			}
			_accounts.add(account);		
		}
	}
	
	/**
	 *  _name is the bank name
	 *  _manager is the bank's manager
	 *  _accounts are the accounts held at the bank
	 */
	private String _name;
	private Person _manager;
	private ArrayList<BankAccount> _accounts;
	
}
