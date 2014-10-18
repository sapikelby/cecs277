import java.net.*;
import java.io.*;
import java.sql.*;
import java.util.*;
import java.lang.*;
import java.security.*;

import javax.crypto.*;

import java.security.SignatureException;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Mac;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
/*
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.*;

import com.google.common.*;
import com.google.common.io.CharStreams;
import com.google.common.io.InputSupplier;
 */



public class GreetingServer extends Thread
{
	private ServerSocket serverSocket;
	private String memType;
	private int memberSerial;
	private static Connection con;
	private Hashtable<Integer, String> lookupTable; 
	/**
	 * Initializes server on specified port
	 * @param port is the specified port waiting for client connection
	 * @throws IOException
	 * @throws ClassNotFoundException 
	 */
	public GreetingServer(int port) 
	{
		try {
			connectDB();
			serverSocket = new ServerSocket(port);
			// socket timeout is 10 seconds
			serverSocket.setSoTimeout(10000);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	/**
	 * Multithreading is used in order to keep multiple connections up on the same port
	 */
	public void run() 
	{

		// generates lookup table for codes once the server is up and running
		generateLookupTable();
		while(true)
		{
			/* ORDER OF EXECUTION
			 * OBTAIN IP ADDRESS FROM CLIENT
			 * CONNECT TO DB, GET SERIAL AND MEMTYPE FOR THIS CLIENT
			 * GET INPUTSTREAM 
			 * DECRYPT INPUT STREAM
			 * THEN WHILE LOOP WILL READ ALL CODES SENT FROM THIS CLIENT UNTIL 0 BYTES ARE LEFT
			 * EACH CODE WILL BE TURNED TO A STRING USED TO CALL A FUNCTION THAT PERTAINS TO THE CODE MESSAGE
			 * FUNCTION IS CALLED FOR THAT CODE
			 * DATABASES ARE UPDATED, USERS ARE UPDATED, ETC... IT DEPENDS ON THE CODE SENT
			 */
			try
			{
				System.out.println("[CC]Waiting for client on port " +
						serverSocket.getLocalPort() + "...");
				Socket server = serverSocket.accept();
				System.out.println("[CC]Just connected to "
						+ server.getRemoteSocketAddress());
				DataInputStream in =
						new DataInputStream(server.getInputStream());

				// gets IP of connected client
				String IPStr = server.getRemoteSocketAddress().toString();
				int index = IPStr.indexOf(":");
				String IP = IPStr.substring(1,index);

				// update memberType and memSerial for this client
				updateTypeSerial(IP);

				// in.available returns the number of bytes that can be read from the inputstream
				// when those bytes are zero, we have no more to read
				while(in.available() > 0) // there is at least 4 bytes to read (an int's size)
				{
					//System.out.println("inside");
					byte[] bArr = null;
					//String decodeMess = lookupCode(in.read(arr,0,18));
					//int len=in.read(bArr, 0, MyConstants.TYPE_LENGTH+16); // reads bytes and stores them in b
					bArr= readSocket (server);
					int len= bArr.length;
					if (len==0){
						//error
					}
					String memType = "";
					for(int i=0;i<MyConstants.TYPE_LENGTH; i++) {
						memType = memType + (char)bArr[i];
					}

					System.out.println(lookupCode(Integer.parseInt(memType)));

					char memID[]= new char[16]; 
					for(int i=MyConstants.TYPE_LENGTH;i<MyConstants.TYPE_LENGTH+16; i++) {
						memID[i-MyConstants.TYPE_LENGTH] = (char)bArr[i];
					}

					/*
					for (byte b:bArr)
			         {
			            // convert byte into character
			            char c = (char)b;

			            // print the character
			            System.out.print(c+" ");
			         }
					 */
					// check for the member type
					switch(lookupCode(Integer.parseInt(memType))) {
					case "DVP": DVP_Handshake(memID);

					break;
					case "SP" : //SP_Handshake(memID); 
						break;
					default :
						//error;
					}

					//System.out.println("Function " +  decodeMess + "failed");


					System.out.println(in.available()); // after reading 4 bytes, prints the number of bytes available to read

				}
				//System.out.println("First int: " + in.readInt()); // was in.toString()

				//String messageMeaning = lookupCode(in.readInt());

				//System.out.println(messageMeaning);

				// incoming message
				//System.out.println("before stream");

				// Converting stream to string

				//System.out.println("'" + myString + "'");

				//System.out.println("hello");
				//System.out.println(in.readUTF());

				OutputStream outToClient = server.getOutputStream();

				DataOutputStream out =
						new DataOutputStream(outToClient);


				System.out.println("after");

				out.writeInt(000000000);  // Server communicating back to SP/DVP
				/*
            out.writeUTF("[CC]Thank you for connecting to "
              + server.getLocalSocketAddress() + "... serial num: " + memberSerial + "\nGoodbye!");
            System.out.println("[CC]Thank you for connecting to "
                    + server.getLocalSocketAddress() + "... serial num: " + memberSerial +
                    "\nGoodbye!");
				 */


				server.close();
			}
			catch(SocketTimeoutException s)
			{
				System.out.println("Server says: Socket timed out!");
				break;
			}
			catch(IOException e)
			{
				e.printStackTrace();
				break;
			}

		}
	}

	
	public static byte[] readSocket(Socket server) {
		byte[] bArr = null;
		try {
			OutputStream outToClient = server.getOutputStream();
			InputStream inputClient = server.getInputStream();
			DataInputStream in = new DataInputStream(inputClient);
	
			//String decodeMess = lookupCode(in.read(arr,0,18));
			int len=in.read(bArr); // reads bytes and stores them in b
			if (len==0){
			}
			//error
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		return bArr;

	}
	
	public static byte[] MyPKCS7(byte[] arrInput) {
		int size = arrInput.length;
		int padL = 16 - (size % 16);
		
		byte pad = (byte) (padL % 16);
		byte[] output = new byte[size+padL];
		System.out.println(pad);
		
		for(int i=0; i<size;i++) {
			output[i]=arrInput[i];
			
		}
		
		for(int i=size; i<size+padL;i++) {
			output[i]=pad;
			
		}
		
		return output;
	}
	
	public static byte[] MyPKCS7Reverse(byte[] arrInput) {
		
		int size = arrInput.length;
		if (size% 16 !=0){
			return null;
			// screw you!
		}
		
		byte Pad= arrInput[size-1];
		int padL= (int) Pad;
		byte output []= new byte [size-padL];
		for (int i= size-1; i> size-padL-1; i--){
			if (arrInput[i]!=Pad){
				// error
				System.out.println("Padding error");
				//error = true;
				return null;
			}
		}
		
		for (int i=0; i< size-padL; i++){
			output[i]=arrInput[i];
		}
		
		return output;
	}
	
	// we know it's DVP after reading socket... so call DVP_Handshake
	// After reading the socket, we will know the memType since we'll read the data input stream of the socket
	// only the DVP_ID is being passed 
	
	private static byte[] retrieveIV(byte [] buffer) {
		byte[] iv = null;
		for(int i=0;i<16;i++) {
			iv[i] = buffer[i];
		}
		return iv;
	}
	
	private static byte[] retrieveNONIV(byte [] buffer) {
		int len= buffer.length;
		byte[] output = null;
		for(int i=16;i<len;i++) {
			buffer[i] = buffer[i];
		}
		return output;
	}
	
	private static void DVP_Handshake(char[] dvp_ID, Socket server) { 
	
		
		
		
		String strSK = String.valueOf(dvp_ID);
		String dvpPass = "";
		int dvpSN = 0;
		try 
		{
			OutputStream outToClient = server.getOutputStream();
			InputStream inputClient = server.getInputStream();
			DataOutputStream out = new DataOutputStream(outToClient);
			DataInputStream in = new DataInputStream(inputClient);
			PreparedStatement statement = con.prepareStatement("select dvp_pass, dvp_sn from dvp where dvp_id = ?");
			statement.setInt(1, Integer.parseInt(strSK));
			ResultSet result = statement.executeQuery();
			char dvpP[]; // dvp Password in char array representation

			// database retrieval
			while(result.next())
			{
				//System.out.println(result.getString(1) + " " + result.getString(2));
				dvpPass = result.getString("dvp_pass");
				//dvpP = dvpPass.toCharArray();
				dvpSN = result.getInt("dvp_sn");
				System.out.println(dvpPass);

			}

			// generates rand (rand byte array created)
			SecureRandom random = new SecureRandom();
			byte randN[] = new byte[16];  // initially 16
			random.nextBytes(randN);
			
			out.write(randN); // write randN to socket
			 
			
			
			// creates hMac(randN)
			Mac mac;
			byte[] hashedkey;
			byte[] hashedkey2;
			
			SecretKeySpec hkey = new SecretKeySpec(dvpPass.getBytes("UTF-8"), "HmacSHA256");
			mac = Mac.getInstance(hkey.getAlgorithm());
			mac.init(hkey);
			hashedkey = mac.doFinal(randN);
			
			SecretKeySpec AESKey = new SecretKeySpec(hashedkey, "AES");

			// read from socket... expect AES of (DVPID, new random number)
			// IV is always the first 16 bytes
			byte [] bArr= readSocket(server);
			
			byte [] iv = retrieveIV(bArr);
			byte [] ccipher= retrieveNONIV(bArr);
			// go and decrypt the remaining using extracted iv
			
			
	        SecretKeySpec secret = new SecretKeySpec(hashedkey, "AES");
	        Cipher cipher = Cipher.getInstance("AES/CBC");
	        AlgorithmParameterSpec paramSpec = new IvParameterSpec(iv);
	        cipher.init(Cipher.DECRYPT_MODE, secret, paramSpec);
	        byte[] decryptedToken = cipher.doFinal(ccipher);
	        
	        // undo the padding
	        byte[] message= MyPKCS7Reverse(decryptedToken);
	        // now message should be DVP_ID and a new random number
	        byte [] New_rand= null;
	        for (int i=0; i<16; i++){
	        	if (message[i]!= (byte) dvp_ID[i]){
	        		// send an error
	        		// maybe drop the connection
	        	}
	        }
	        for (int i=16; i<message.length; i++){
	        	New_rand[i-16]=message[i];
	        }
	        
			hashedkey2 = mac.doFinal(New_rand);
			SecretKeySpec AESKey2 = new SecretKeySpec(hashedkey2, "AES");
	        
			  // initially 16
			byte [] key3 = new byte [32];
			random.nextBytes(key3);
	
			random.nextBytes(iv);
			byte [] message2= null;
			for (int i=0; i<64; i++){
				if (i<32){
					message2[i]=key3[i];
				}
				if ( (i>31) && (i<48) ){
					message2[i]=New_rand[i-32];
				}
				if (47<i){
					message2[i]=(byte) dvp_ID[i-48];
				}
				
			}
			
	        AlgorithmParameterSpec paramSpec2 = new IvParameterSpec(iv);
	        cipher.init(Cipher.ENCRYPT_MODE, AESKey, paramSpec);
	        
	        // First pad message2 using PKCS7
	        // Now go ahead and encrypt message 2 and write it into socket
	        
		}
		catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		} catch(SQLException sql){
		sql.printStackTrace();
		}
	// 1. Query database to get dvp password and dvp_sn
	// then do protocol 
	// generate rand number
		catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

}

// works
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


/**
 * Based on client connected (SP or DVP) private data such as memType, memberSerial are updated
 * Allows us to update tables accordingly based on connected user
 * @param IP is the IP of the member connecting to server
 * @throws UnknownHostException
 * @throws ClassNotFoundException
 */
private void updateTypeSerial(String IP) 
{
	//InetAddress IP = InetAddress.getLocalHost();
	//System.out.println("IP address is: " + IP.getHostAddress());
	//int memberSerial = 0;
	//String memType = "";
	//Class.forName("com.mysql.jdbc.Driver");

	try 
	{
		//System.out.println(IP);
		//Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/command_control","localhost","");
		//System.out.println("[CC]Connection successful");
		PreparedStatement statement = con.prepareStatement("select serial_number, member_type from active_members where member_ip =" + "'" + IP + "'");

		ResultSet result = statement.executeQuery();

		while(result.next())
		{
			//System.out.println(result.getString(1) + " " + result.getString(2));
			memType = result.getString("member_type");
			memberSerial = result.getInt("serial_number");
			System.out.println("Member: " + memType + " Serial:" + memberSerial);
		}
	}
	catch(SQLException e)
	{
		System.out.println(e.getMessage());
	}


	// get IP from user, call a function
	// based on IP, return it's member type and update tables

}

/*
   /**
 * Converts data input stream into a usable string
 * @param in is the data input stream coming in from client
 * @throws IOException
 */
/*
   private void convertStreamToString(DataInputStream in) 
		   throws IOException {

	   StringBuilder textBuilder = new StringBuilder();
	    try (Reader reader = new BufferedReader(new InputStreamReader
	      (in, Charset.forName(StandardCharsets.UTF_8.name())))) {
	        int c = 0;
	        int len=in.toString().length();
	        System.out.println(len);
	        for (int i=0; i<= len; i++)
	        {
	        	c = reader.read();
	        	//while ((c = reader.read()) != -1) {
	            textBuilder.append((char) c);
	        }

	        System.out.println("Full text: " + textBuilder);
	    }

	   //String text = CharStreams.toString(new InputStreamReader(in, "UTF-8"));
	   //System.out.println(text);

   }
 */



private static void getPlainMessage(String memType, String message)
{

}

private static String getMessage(String memType, String message)
{
	//... based on the member type and contents of the message -- do 
	// a switch case where you call different functions based on
	// what the message might be
	// the function called will be that of that memberType
	// notice that all functions can update/drop for any type... they act kinda 
	// like polymorphic functions in the sense that you only need to pass
	// in memType and serial num in order to update a table
	// no need to write separate functions for each type
	// 

	// look at java.crypto
	// deciphers message from DVP or SP
	String decryptedMessage ="";
	return decryptedMessage;
}

// for new members (DVPs and SPs) you need to create an update function to populate the SP and DVP table
// look at SN to see if it's new -- in the real word the software will probably already have the SN
// SN meaning starts 1 DVP
// starts with 2 CLient
// starts with 3 User
// SP_CC handshake connects java and php code

/**
 * Adds new member to SP/DVP table as well as to the ACTIVE MEMBER table
 * @param memberType is the member type connnecting
 * @param serial is the member serial
 * @param IP is the member IP 
 * @throws SQLException
 */
// this will be used when we get new DVPs, insert into dvp table or static_proxy or user table depending on member type
// return SN or -1 if it fails
// fill in the password with a randomly generated pass
// dvp_sn and dvp_id are both auto incrememented
// just to add to DVP and SP first time ever 

/*
	private static void newMemberRegistration(String memberType, String IP) 
	{
		//Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/command_control","localhost","");
		// all the rest of the attributes are populated here: include password and signature
		try {
			PreparedStatement statement;
			PreparedStatement statementActiveMembers = null;
			if (memberType == "DVP")
			{
				statementActiveMembers = con.prepareStatement("insert into active_members values" + "(" + "'" + IP + "'" + "DVP," + "'" + serial + "'" + ",session_key..kajsdflkjas" +")");
				statement = con.prepareStatement("insert into dvp values" + "(" + "'" + serial + "'" + "," + "'" + IP + "'" + "," + "now()" + ")");
			}
			else if (memberType == "SP")
			{
				statementActiveMembers = con.prepareStatement("insert into active_members values" + "(" + "'" + IP + "'" + "SP," + "'" + serial + "'" + ",session_key..kajsdflkjas" +")");
				statement = con.prepareStatement("insert into static_proxy values" + "(" + "'" + serial + "'" + "," + "'" + IP + "'" + "private_key...sdkjfal;ksf," + "now(), 3" + ")");

			}
			else // user
			{
				//statementActiveMembers = con.prepareStatement("insert into active_members values" + "(" + "'" + IP + "'" + "," + "'" + serial + "'" + ",session_key..kajsdflkjas" +")");
				statement = con.prepareStatement("insert into dvp user" + "(" + "'" + serial + "'" + "," + "2, testing@gmail.com, 5)");
			}

			statement.executeQuery();  // executes query for either the DVP or SP
			statementActiveMembers.executeQuery();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		// populate either the DVP, SP, or User table based on who the member is...
		// also make sure to update the active members table
		// anytime a member is dropped, update the status of that user and remove it from active members
	}
 */
/**
 * IMPLEMENTED AFTER THE HANDSHAKE
 * @param memType
 * @param Serial
 * @param IP
 * @param public_key
 */
private static void returningMember(String memType, int serial, String IP, int public_key) { // implemented after the handshake 
	// tables dvp and static_proxy, choose table based on member type
	// update fields within tables where SN == DVP_SN or SP_SN
	// upate public key of SP, DVP pk = 0  
	// 
	try {

		boolean typeQuery = true; // true if insert
		PreparedStatement statement;
		// checks to see if DVP or SP member exists by checking SN against active member tables
		statement = con.prepareStatement("select count(*)  as total from active_members where serial_number = ?");
		statement.setInt(1, serial);

		ResultSet countRS = statement.executeQuery();
		countRS.next(); // moves pointer to first element
		int records = countRS.getInt("total");


		if(memType=="SP") {
			if(records == 1) {
				typeQuery = false;
				statement = con.prepareStatement("update static_proxy set sp_pk = " + public_key + " where sp_sn = ?");
				statement.setInt(1, serial);
			}
			else { // not an existing member
				statement = con.prepareStatement("insert into static_proxy values(?,?,?,?,?,?,?,?,?,?,?) where sp_sn = " + serial + ")");
				statement.setInt(1, serial); // updates serial
				statement.setInt(2, 5465465); // this is ID --> auto incremented in table
				statement.setString(3, "password");
				statement.setString(4,IP); // sets IP 
				statement.setInt(5, 0); // zero num of dvps since it has just been added
				statement.setDate(6, new java.sql.Date(System.currentTimeMillis()));
				statement.setDate(7, new java.sql.Date(System.currentTimeMillis()));
			}
		}
		// still need to finish this part
		else if(memType=="DVP") {
			if(records == 1)
				statement = con.prepareStatement("insert into static_proxy values" + "(" + "'" + serial + "'" + "," + "'" + IP + "'" + "private_key...sdkjfal;ksf," + "now(), 3" + ")");
		}


	}
	catch(SQLException e) {
		e.printStackTrace();
	}

}

/**
 * 
 * Drops member from DVP or SP table and ACTIVE MEMBER table accordingly
 * @param memType
 * @param serial
 * @throws SQLException 
 */
private static void memberDrop(String memType, int serial) 
{
	System.out.println("Member will be dropped!");



	if (memType == "DVP")
	{
		changeStatus("DVP", serial, 0);
	}
	else if (memType == "DVP") {
		changeStatus("SP", serial, 0);
	}

	//System.out.println("Member: " +  serial +  " will be dropped");
	// based on passed serial, user of that serial will be dropped from table or status set to 0
}

//private static void updateLastTime(String memType, int serial, Calendar date) throws SQLException
// fixed issue with DB, on updatequery use executeUpdate and not executeQuery
private void updateLastTime() throws SQLException // no longer needs parameters since the client is identified from the beginninng
{
	//Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/command_control","localhost","");

	try {
		PreparedStatement statement;
		if (memType.equals("DVP"))
		{
			//UPDATE `command_control`.`dvp` SET `last_active` = '2014-07-31 00:22:26' WHERE `dvp`.`dvp_sn` = 123456789;
			statement = con.prepareStatement("update dvp set last_active = NOW() where dvp_sn = " +  "'" + memberSerial + "'");
		}
		else 
		{
			statement = con.prepareStatement("update static_proxy set last_time_alive = now() where sp_sn = " +  "'" + memberSerial + "'");
		}

		statement.executeUpdate();
	}
	catch(SQLException e) {
		e.printStackTrace();
	}
}

/**
 * call this function when dropping a member, 
 * do not delete the data from the database when dropping
 * a member, instead simply change their status to 0 (inactive)
 * @param memType
 * @param serial
 * @param status
 * @throws SQLException 
 */
private static void changeStatus(String memType, int serial, int status) 
{
	//Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/command_control","localhost","");

	try {
		PreparedStatement statement;
		if (memType == "DVP")
		{
			statement = con.prepareStatement("update dvp set status = " + "'" + status + "'" + "where dvp_sn = " +  "'" + serial + "'" + ")");
		}
		else 
		{
			statement = con.prepareStatement("update static_proxy set status = " + "'" + status + "'" + "where sp_sn = " +  "'" + serial + "'" + ")");
		}

		statement.executeQuery();
	}
	catch (SQLException e) {
		e.printStackTrace();
	}
}

// leave Summit and Sam
// openssl in java read more about it
// this would used for the handshake

private static void assignSP_to_DVP(int DVP_SN, int SP)
{
	// status, time stamps should be updated
	// sp_ip and sp_pk should be updated and so should status and last active
	// the DVP_SP will be updated, as well as that of the SP and DVP being assigned...
	// things like last time used will be updated on those 3 tables
	// update # of dvps assigned to the sp in the static_proxy table


	// IGNORE ABOVE
	// choose SP_SN from table static_proxy where status == 1 (alive) and num of DVP's is minimum 
	// query select sp_sn and num_of_dvps where status = 1, choose sp with minimum amt of connection
	// update num of dvps for that SP, where sp = sp_sn with ++;
	// then last thing --> go in DVP table and update sp_ip and sp_pn, where dvp_sn = dvp_sn

	// return status of query 1 is successful, 0 is failed 
	// if successful in executing, then call returningMember
	// then last blue line gets executed (SP_IP, SP info gets sent to DVP)



}

// request is received by mail server
private static void assignUser_DVP(int USER_SN, int DVP_SN) 
{
	//Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/command_control","localhost","");
	try {
		PreparedStatement statement;

		// Add record to the user_dvp table
		statement = con.prepareStatement("insert into user_dvp values" + "(" + "'" + USER_SN + "'" + ",'" + DVP_SN + "'" + "now()" +")");
		statement.executeQuery();  // executes query 

		// update number of connections in DVP table
		statement = con.prepareStatement("update dvp set num_of_connections = num_of_connections + 1" + "where dvp_sn = " +  "'" + DVP_SN + "'" + ")");
		statement.executeQuery();

		// update number of DVPs for user
		statement = con.prepareStatement("update user set num_of_dvps = num_of_dvps + 1" + "where u_sn = " +  "'" + USER_SN + "'" + ")");
		statement.executeQuery();
	}

	catch(SQLException e) {
		e.printStackTrace();
	}

	// assigns a DVP to a user
	/// DVP and user table will also get updated when this table is updated
	// update # of connections assigned to the dvp in the DVP table

}

/**
 * Connects to local database
 * @throws SQLException if connection is unsuccessful
 * @throws ClassNotFoundException 
 */
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


// codes are still passed but in the form of char array char[] = {'4' , '5'}
public void generateLookupTable() {
	String messageCodes[] = {"Drop", "New user", "New DVP", "Update last time", "Assign UserDVP", 
			"Assign SPDVP", "Ending Connection", "DVP", "SP", "DVP-Dead"}; 
	// SP tells CC that DVP is Dead, only Ping SP when assigning DVP to SP...
	// if SP is dead, then update table and assign DVP to new SP (that will all be internal)
	// internal process - if DVP is dead, user of that DVP and SP servicing it get reassigned
	// we know an SP is dead, if an existing DVP tries to connect to us

	lookupTable = new Hashtable<Integer, String>();
	int counter = 0;
	for(int i=8000;i<100000 && counter < messageCodes.length;i=i+14) {
		lookupTable.put(i, messageCodes[counter]);
		counter++;
	}

	System.out.println(lookupTable.toString());
}

// lookup table for message codes 
/**
 * 
 * @param code is the lookup code passed in
 * @return the string representation of that message used to call a function
 */
private String lookupCode(int code) 
{

	/*
	   String message = "";
	   switch(code) 
	   {
	   case 8000: 
		   message = "Drop";
		   break;
	   case 8014: 
		   message = "New user";
		   break;
	   case 8028:
		   message = "New DVP";
		   break;
	   case 8042: 
		   message = "Update last time";
		   break;
	   case 8056: 
		   message = "Assign UserDVP";
		   break;
	   case 8070: 
		   message = "Assign SPDVP";
		   break;   
	   case 8084: 
		   message = "Ending Connection";
		   break;
	   }
	 */
	//return message;
	return lookupTable.get(code);

}





/**
 * Deciphers message sent by client and calls a function according to message
 * @param message is the message as a string
 * @param serial is the serial of the member being dropped or assigned 
 * @param IP is the IP of the server that connects to CC
 * @throws SQLException 
 */
private void callFunction(String message, int serial, String IP) 
{
	// switch case based on command
	// commands are DROP, NEW USER, NEW DVP, ASSIGN_DVP
	// UPDATE_LAST_TIME
	int msgCode = 0;
	try {
		if (message.equals("Drop"))
		{
			msgCode = 1;
			memberDrop(memType, serial);
		}
		else if (message.equals("New User") || message.equals("New DVP"))
		{
			msgCode = 2;
			newMember(memType, memberSerial, IP);
		}
		else if (message.equals("Update last time"))
		{
			msgCode = 3;
			updateLastTime();
		}
		else if (message.equals("Assign UserDVP"))
		{
			msgCode = 4;
			assignUser_DVP(serial, memberSerial);
		}
		else if (message.equals("Assign SPDVP"))
		{
			msgCode = 5;
		}
	}
	catch (SQLException e) {
		e.printStackTrace();
	}

	/*
	   // call functions based on msgCode above
	   switch(msgCode) 
	   {
	   case 1:
		   memberDrop(memType, serial);
	   case 2:
		   newMember(memType, memberSerial, IP);
	   case 3:
		   //updateLastTime(memType, memberSerial, );
	   case 4: 
		   // serial == is the user serial, memberSerial is that of the SP or DVP
		   assignUser_DVP(serial, memberSerial);
	   }
	 */
}


// NEW FUNCTIONS TO BE WRITTEN 
private static void SP_Handshake() {

}





public static void main(String [] args) 
{
	//int port = Integer.parseInt(args[0]);
	int port = 6078;

	Thread t = new GreetingServer(port);
	t.start();



}
}