// Create a BankAccount that locks 
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
public class BankAccount 
{
	public BankAccount(double originalBalance)
	{
		mBalance = originalBalance;
	}
	
    public synchronized double getBalance()
	{
		return mBalance;
	}
	
	public void makeDeposit(double amount) // would be like producing
	{
		lock.lock();
		try {
			mBalance += amount;
			depositedMoney.signal();
		}
		finally
		{
			lock.unlock();
		}
		
	}
	
	public void makeWithdrawal(double amount) // would be like consuming
	{
		lock.lock();
		try {
			
			while (mBalance - amount < 1)
			{
				System.out.println("Waiting to withdraw");
				depositedMoney.await();
			}
			mBalance -= amount;
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
		finally
		{
			lock.unlock();
		}
	}
	
	private double mBalance;
	private final Lock lock = new ReentrantLock();
	private final Condition depositedMoney = lock.newCondition();
		
}
