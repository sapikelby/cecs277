/**
*
* CECS 277 Spring 2014.   Assignment 7
*
* @author Kelby Sapien 3260
* @author Paul Kim 6816
*
*/

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *	Basic bank account representation with the ability to
 *	make deposits and withdrawals
 */
public class BankAccount 
{
	/**
	 * Custom constructor used to initialize the bank account to a 
	 * specified initial balance
	 * @param originalBalance is the account's original balance
	 */
	public BankAccount(double originalBalance)
	{
		mBalance = originalBalance;
	}
	
	/**
	 * Returns the bank account's balance
	 * Synchronized method guarantees that one thread finishes execution, while all
	 * other threads wait until the first thread is done with the object
	 * @return the balance of account
	 */
    public synchronized double getBalance()
	{
		return mBalance;
	}
	
    /**
     * Makes deposit to account 
     * lock is used to control the threads that want to manipulate mBalance 
     * lock is unlocked when thread is done with object- other threads have access to lock after unlock
     * depositedMoney condition is signaled to notify other thread that the condition 
     * may now be true
     * @param amount is the amount deposited to the account
     */
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
	
	/**
	 * Makes withdrawal to account
	 * lock is used to control the threads that want to manipulate mBalance
	 * lock is unlocked when thread is done with object - other threads have access to lock after unlock
	 * depositedMoney condition makes current thread wait until notified by another thread
	 * that condition may now be true
	 * @param amount is the amount withdrawn
	 */
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
	
	/**
	 * 	mBalance is the account's balance
	 * 	Lock is the lock used to control the manipulation of the account's balance
	 * 	lock is unlocked when thread is done with object - other threads have access to lock after unlock
	 * 	depositedMoney is the condition used to notify when a condition may have been been met
	 */
	private double mBalance;
	private final Lock lock = new ReentrantLock();
	private final Condition depositedMoney = lock.newCondition();
		
}
