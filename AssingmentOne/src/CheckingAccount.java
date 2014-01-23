import java.util.Calendar;
import java.util.ArrayList;

public class CheckingAccount {

	public CheckingAccount(Person person)
	{
		owner = person; 
		balance = 0; 
		transactions = 0; 
	}
	
	public Person getOwner()
	{
		return owner; 
	}
	
	public double makeDeposit(Calendar date, double amount)
	{
		balance = balance + amount; 
		transactions++; 
		
		return balance; 
	}
	
	public double makeWithdrawal(Calendar date, double amount)
	{
		balance = balance - amount; 
		transactions++; 
		
		return balance; 
	}
	
	public double getBalance()
	{
		return balance; 
	}
	
	private double balance; 
	private int transactions;
	private Person owner; 
	//private 
	
}