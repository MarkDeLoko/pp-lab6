import java.net.*;
import java.util.Random;
import java.io.*;

public class Server {
	public static void main(String[] ar) {
		int port = 6666;

		try {
			ServerSocket ss = new ServerSocket(port); 
			System.out.println("Waiting for a client...");

			Socket socket = ss.accept(); 
											
			System.out.println("Got a client");
			System.out.println();

			InputStream sin = socket.getInputStream();
			OutputStream sout = socket.getOutputStream();

			DataInputStream in = new DataInputStream(sin);
			DataOutputStream out = new DataOutputStream(sout);

			String line = null;
			while (true) {
				int summ = 0;
				line = in.readUTF(); 
				System.out.println("Got a number : " + line);
				try {
					for (int i = 0; i < Integer.parseInt(line); i++)
						summ = summ + i;
					line = Integer.toString(summ);
				} catch (java.lang.NumberFormatException f) {
					line = "Not correct data";
				};				
				out.writeUTF(line); 
				out.flush(); 
				System.out.println("Waiting for the next line...");
				System.out.println();
			}
		} catch (Exception x) {
			x.printStackTrace();
		}
	}
}
