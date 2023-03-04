import java.io.*;
import java.net.*;

public class DemoTCPSender{
	
	private static final String HOST_ADDR = "127.0.0.1";
	
	public static void main(String args[]){
		try{
			String _str; //declare variable for later use
			
			Socket _socket = new Socket(HOST_ADDR, 12345); //create the client socket
			PrintWriter _writer = new PrintWriter(_socket.getOutputStream(), true); //prepare the output stream
			
			BufferedReader _reader = new BufferedReader(new InputStreamReader(System.in));//prepare to get user input from standard input stream
			
			System.out.println("Type something or press Ctrl-C to end input");
			while((_str = _reader.readLine()) != null){//get user input
				_writer.println(_str);//write data to output stream
			}//repeat read and send user input
			
			_socket.close();//or close the connection
			System.out.println("Connection closed");
		}catch(UnknownHostException uhe){
			System.out.printf("Host [%s] not found\n", HOST_ADDR);
		}catch(IOException ioe){
			ioe.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}