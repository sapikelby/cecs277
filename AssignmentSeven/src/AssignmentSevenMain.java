/**
 * CECS 277 Spring 2014 Assignment 7
 * @author murgolo
 */

import javax.swing.JOptionPane;


public class AssignmentSevenMain
{
	public static void main(String[] args)
	{	
		double originalBalance = Integer.parseInt(
				   JOptionPane.showInputDialog("Enter original Bank Account balance")
				   );
		
		BankAccount account = new BankAccount(originalBalance);
		
		CreateTransactions makeDeposits = new CreateTransactions(account, "D",0,200);
		CreateTransactions makeWithdrawals = new CreateTransactions(account, "W",100,200);
		
		Thread t1 = new Thread(makeDeposits);
		Thread t2 = new Thread(makeWithdrawals);
		
		t2.start();
		t1.start();
		
		while(true)
		{
			double bal = account.getBalance();
			if(bal < 0) System.out.println("Negative Balance: " + bal);
		}	   
	}
}
