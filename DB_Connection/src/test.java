import java.io.CharArrayReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class test {
	
	private static Connection con;
	
	public static void main(String[] args) {
		connectDB();
		char SK[] = {'a', 'b', 'd'};
		
		String IP = "127.0.0.2";
		String memType = "DVP";
		int SN = 5464;
		
		//addMemberToActiveTable(IP, memType, SN, SK);
		
		MyPKCS7(SK);
		
		
		
	}
	
	private static void addMemberToActiveTable(char SK[]) {
		Reader reader = new CharArrayReader(SK);
		char arr[] = new char[3]; 
		
		int data = 0;
		char c = 'a';
		try {
			data = reader.read();
			int i =0;
			while(data != -1) {
				  //do something with data
				
				data = reader.read();
				System.out.println(data);
				//arr[i] = (char)data;
				c = (char) data; 
				System.out.println("char: " + c);
				i++;
			}
			
			System.out.println("arr" + arr.toString());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	private static void  addMemberToActiveTable(String IP, String memType, int SN, char SK[]) {
		//Reader reader = new CharArrayReader(SK);
		String strSK = String.valueOf(SK);
		/*	
		 * Reader reader = new CharArrayReader(SK);
		char arr[] = new char[3]; 
		int data = 0;
		char c = 'a';
		try {
			data = reader.read();
			int i =0;
			while(data != -1) {
				  //do something with data
				
				data = reader.read();
				System.out.println(data);
				//arr[i] = (char)data;
				c = (char) data; 
				System.out.println("char: " + c);
				i++;
			}
			
			System.out.println("arr" + arr.toString());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 * 
		 * 
		 */
		try {
			PreparedStatement statement;
			statement = con.prepareStatement("update active_members set member_type = '" + memType + "',serial_number = '" + SN + "',session_key = '" + strSK +"' where member_ip = ?");
			statement.setString(1, IP);
			
			int result = statement.executeUpdate();
			if(result == 0) {
				System.out.println("adding");
				statement = con.prepareStatement("insert into active_members (member_ip, member_type, serial_number, session_key) values(?,?,?,?)");
				statement.setString(1, IP);
				statement.setString(2, memType);
				statement.setInt(3, SN);
				statement.setString(4, strSK);
				statement.executeUpdate();
				
			}
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}

	}
	
	public static void connectDB() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/command_control","localhost","");
		}
		catch(SQLException e) {
			System.out.println("Database error");
			e.printStackTrace();
		}
		catch(ClassNotFoundException c) {
			System.out.println("Driver error");
			c.printStackTrace();
		}
	}
	
	public byte[] MyPKCS7(byte[] arrInput) {
		int size = arrInput.length;
		
		int padL = 16 - (size % 16);
		byte pad = (byte) (padL % 16);
		System.out.println(pad);
		
		byte[] output = null;
		return output;
	}
		
}
