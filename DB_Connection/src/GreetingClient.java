import java.net.*;
import java.sql.SQLException;
import java.io.*;

// email server for the client
// take care of the handshake
// assigning 

public class GreetingClient
{
	//int const handShake = 124;
   public static void main(String [] args)
   {
      //String serverName = args[0];
	   String serverName = "localhost";
	   int port = 6078;
      //int port = Integer.parseInt(args[1]); 
      try
      {
         System.out.println("[Client]Connecting to " + serverName
                             + " on port " + port);
         Socket client = new Socket(serverName, port);
         System.out.println("[Client]Just connected to "
                      + client.getRemoteSocketAddress());
         OutputStream outToServer = client.getOutputStream();
         DataOutputStream out =
                       new DataOutputStream(outToServer);
         
         //OutputStreamWriter writer = new OutputStreamWriter(outToServer, "UTF-8");
         int messageCode = 8042;
         int messageCode2 = 8056;
         int messageCode3 = 8070;
         
         // messageCode will let us know what the user wants to do... a look up table will then be used to decipher 
         // that message
         
         // have a definition file for all the codes being passed
         // message is sent as an int and read as an int
         
         //String message = "[Client]Drop user 12345678" + client.getLocalSocketAddress();
         //System.out.println(message);
         //out.writeUTF("[c]Hello from "
         //             + client.getLocalSocketAddress());
         //writer.write(message);
         //out.write(message.getBytes("UTF-8"));
         out.writeInt(messageCode);
         out.writeInt(messageCode2);
         out.writeInt(messageCode3);
         
         
         InputStream inFromServer = client.getInputStream();
         DataInputStream in =
                        new DataInputStream(inFromServer);
         
         
         
         while(in.available() > 0) // there is at least 4 bytes to read (an int's size)
		   {
			   //System.out.println("inside");
			   //String decodeMess = lookupCode(in.readInt());
        	 //int serverInput = in.readInt();
        	 //System.out.println(decodeMess);
        	 //System.out.println(serverInput);
        	 System.out.println("Server says " + in.readInt());
        	 System.out.println(in.available()); // after reading 4 bytes, prints the number of bytes available to read
        	 
		   }
         //out.writeInt(854654);
         client.close();
      }catch(IOException e)
      {
         e.printStackTrace();
      }
   }
}