import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Calendar;
import java.util.ArrayList;


public class AssignmentOne 
{
	private static Calendar convertDate(String sDate) 
	{
		Calendar c = Calendar.getInstance();
        int m = Integer.parseInt(sDate.substring(0,2));
        int d = Integer.parseInt(sDate.substring(2,4));
        int y = Integer.parseInt(sDate.substring(4,8));
        c.set(y, m, d);
        
        return c;
	}
	      
	public static void main(String[] args) throws FileNotFoundException 
	{	
		String inputFileName = args[0];
		File inputFile = new File(inputFileName);
		Scanner sc = new Scanner(inputFile);
		
		String s = sc.nextLine();
		int nAccounts = Integer.parseInt(s);
		ArrayList<CheckingAccount> accounts = new ArrayList<CheckingAccount>();
		
		for(int ii = 0; ii < nAccounts; ++ii)
		{
			String fName = sc.nextLine();
			String lName = sc.nextLine();
			String sDate = sc.nextLine();
			Calendar bDate = convertDate(sDate);
	        Person person = new Person(fName, lName, bDate);
	        
	        CheckingAccount account = new CheckingAccount(person);
	        accounts.add(account);
			
	        s = sc.nextLine();
			int nTrans = Integer.parseInt(s);
			
			for(int jj = 0; jj < nTrans; ++jj)
			{
				String sDate1 = sc.nextLine();
				Calendar tDate = convertDate(sDate1);
				
				String sType = sc.nextLine();
				
				s = sc.nextLine();
				double amount = Double.parseDouble(s);
				
				if(sType.equals("D")) account.makeDeposit(tDate, amount);
				else account.makeWithdrawal(tDate, amount);
			}
		}
	
		sc.close();
		
		System.out.println("\nAccount Detail\n");
		for(CheckingAccount account : accounts)
		{
			System.out.println(account);
		}
		
		System.out.println("\nAccount Summary\n");
		for(CheckingAccount account : accounts)
		{
			System.out.println(account.getOwner().getLastName() + ", " +
					           account.getOwner().getFirstName() + ": " +
					           String.format("%.2f", account.getBalance()));
		}
	}
}