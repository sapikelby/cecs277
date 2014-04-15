import java.util.Random;
import java.lang.Thread;

public class CreateTransactions implements Runnable 
{
	public CreateTransactions(BankAccount account, String type, double min, double max)
	{
		mAccount = account;
		mType = type;
		mMin = min;
		mRange = max - min;
		mGenerator = new Random();
	}
	
	@Override
	public void run() 
	{
		while(true)
		{
			double scale = mGenerator.nextDouble();
			double amount = mMin + mRange * scale;
			
			if(mType.equals("D")) mAccount.makeDeposit(amount);
			else mAccount.makeWithdrawal(amount);
			
			try
			{
				Thread.sleep(5000);
			}
			catch(Exception exc)
			{
				System.out.println("Thread sleep inturrupted");
			}
		}
	}
	
	private BankAccount mAccount;
	private String mType;
	private double mMin;
	private double mRange;
	private Random mGenerator;

}
