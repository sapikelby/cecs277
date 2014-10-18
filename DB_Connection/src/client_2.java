import java.net.*;
import java.io.*;
public class client_2 {


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
			int messageCode = 8014;
			int messageCode2 = 8028;

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

			InputStream inFromServer = client.getInputStream();
			DataInputStream in =
					new DataInputStream(inFromServer);

			System.out.println("Server says " + in.readInt());
			//out.writeInt(854654);
			client.close();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}

