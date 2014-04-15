// Create a BankAccount that locks 
public class BankAccount
{
	public BankAccount(double originalBalance)
	{
		mBalance = originalBalance;
	}
	
    public double getBalance()
	{
		return mBalance;
	}
	
	public void makeDeposit(double amount)
	{
		mBalance += amount;
	}
	
	public void makeWithdrawal(double amount)
	{
		mBalance -= amount;
	}
	
	private double mBalance;
		
}
