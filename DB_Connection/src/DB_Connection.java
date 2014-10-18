import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.*;
import java.util.*;


public class DB_Connection {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Class.forName("com.mysql.jdbc.Driver");
		
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/command_control","localhost","");
		
		PreparedStatement statement = con.prepareStatement("select * from dvp");
		
		ResultSet result = statement.executeQuery();
		
		while(result.next())
		{
			System.out.println(result.getString(1) + " " + result.getString(2));
		}
		
		getMemberType();
		
	}
	
	public static String getMemberType() throws UnknownHostException
	{
		InetAddress IP = InetAddress.getLocalHost();
		System.out.println("IP address is: " + IP.getHostAddress());
		
		
		String memberType = "";
		String message = "";
		
		Scanner sc = new Scanner(System.in);		
		
		message = sc.nextLine();
		
		
		
		System.out.println(memberType);
		
		return memberType;
		
		// get IP from user, call a function
		// based on IP, return it's member type and update tables
		
	}

}
