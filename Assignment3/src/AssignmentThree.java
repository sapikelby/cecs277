/**
 * CECS 277 Spring 2014 Assignment 3
 * @author murgolo
 */
//package cecs277;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Calendar;
import java.lang.Exception;

/* Input File format
 * BankName
 * ManagerFirstName
 * ManagerLastName
 * ManagerBirthDate
 * NumberOfAccounts
 * AccountOwnerFirstName
 * AccountOwnerLastName
 * AccountOwnerBirthDate
 * Checking or Savings
 * NumberOfTransactions
 * TransactionDate
 * TransactionType D=deposit, W=withdrawal, F=fee (checking only), I=interest (savings only)
 * TransactionAmount
 * 
 * Accounts repeat NumberOfAccounts times
 * Transactions repeat NumberOfTransactions times
 */

public class AssignmentThree
{
	private static Calendar convertDate(String sDate) 
	{
		Calendar c = Calendar.getInstance();
		int m = Integer.parseInt(sDate.substring(0,2));
        int d = Integer.parseInt(sDate.substring(2,4));
        int y = Integer.parseInt(sDate.substring(4,8));
        
        int month = 0;
        switch(m)
        {
	        case 1: month = Calendar.JANUARY; break;
	        case 2: month = Calendar.FEBRUARY; break;
	        case 3: month = Calendar.MARCH; break;
	        case 4: month = Calendar.APRIL; break;
	        case 5: month = Calendar.MAY; break;
	        case 6: month = Calendar.JUNE; break;
	        case 7: month = Calendar.JULY; break;
	        case 8: month = Calendar.AUGUST; break;
	        case 9: month = Calendar.SEPTEMBER; break;
	        case 10: month = Calendar.OCTOBER; break;
	        case 11: month = Calendar.NOVEMBER; break;
	        case 12: month = Calendar.DECEMBER; break;
	        default: month = Calendar.JANUARY;
        }
        
        c.set(y, month, d);
                
        return c;
	}
	
	public static void outError(String str)
	{
		System.out.println("\n===================================================================");
		System.out.println(str);
		System.out.println("====================================================================\n");
	}
	      
	public static void displayExceptions()
	{
		System.out.println("\nNumber of overdrawn account attempts: " + OverdrawnAccountException.getCount());
		
		System.out.println("Number of duplicate account attempts: " + DuplicateAccountException.getCount());
		
	}
	
	public static void main(String[] args) throws FileNotFoundException 
	{	
		String inputFileName = args[0];
		File inputFile = new File(inputFileName);
		Scanner sc = new Scanner(inputFile);
		
		// Bank Data
		String bankName = sc.nextLine();
		String bankManagerFirstName = sc.nextLine();
		String bankManagerLastName = sc.nextLine();
		String mDate = sc.nextLine();
		Calendar mbDate = convertDate(mDate);
		Person manager = new Person(bankManagerFirstName, bankManagerLastName, mbDate);
		Bank bank = new Bank(bankName, manager);
		
		String s = sc.nextLine();
		int nAccounts = Integer.parseInt(s);
				
		// Accounts in the Bank
		for(int ii = 0; ii < nAccounts; ++ii)
		{
			String fName = sc.nextLine();
			String lName = sc.nextLine();
			String sDate = sc.nextLine();
			Calendar bDate = convertDate(sDate);
	        Person person = new Person(fName, lName, bDate);
	        
	        BankAccount account = null;
	        String type = sc.nextLine();
	        if(type.equals("Checking"))
	        {
	        	account = new CheckingAccount(person);
	        }
	        else
	        {
	        	account = new SavingsAccount(person);
	        }
	        
	        try
	        {
	        	bank.addAccount(account);
	        }
	        catch(DuplicateAccountException exc)
	        {
	        	outError("Duplicate Account: " + exc.getMessage());
	        }
			
	        s = sc.nextLine();
			int nTrans = Integer.parseInt(s);
			
			// Transactions in each account
			for(int jj = 0; jj < nTrans; ++jj)
			{
				String sDate1 = sc.nextLine();
				Calendar tDate = convertDate(sDate1);
				
				String sType = sc.nextLine();
				
				s = sc.nextLine();
				double amount = Double.parseDouble(s);
				
				try
				{
					if(sType.equals("D")) account.makeDeposit(tDate, amount);
					else if(sType.equals("W")) 
					{
						try
						{
							account.makeWithdrawal(tDate, amount);
						}
						catch(OverdrawnAccountException exc)
						{
							outError("Overdrawn Account: " + exc.getMessage());
						}
					}
					else if(sType.equals("F")) 
					{
						if(type.equals("Checking"))
						{
							CheckingAccount cAcc = (CheckingAccount)account;
							cAcc.chargeFee(tDate, amount);
						}
						else
						{
							throw(new Exception("Fees can only be applied to Checking Accounts"));
						}
					}
					else if(sType.equals("I")) 
					{
						if(type.equals("Savings"))
						{
							SavingsAccount sAcc = (SavingsAccount)account;
							sAcc.postInterest(tDate, amount);
						}
						else
						{
							throw(new Exception("Interest can only post to Savings Accounts"));
						}
					}
				}
				catch(Exception exc)
				{
					System.out.println("\nCaught exception: " + exc.getMessage());
				}
			} // end for each transaction
		}// end for each account
				
		sc.close();
		
		System.out.println(bank.getDisplay(true));
		System.out.println(bank.getDisplay(false));
		
		displayExceptions();
	}
}