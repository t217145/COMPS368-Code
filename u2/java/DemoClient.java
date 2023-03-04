import java.io.*;
import java.net.*;

public class DemoClient{
	
	public static void main(String args[]){
		try{
			String _str = "";	
			
			Socket _socket = new Socket("127.0.0.1", 12345); //Prepare TCP socket and connect to server
			BufferedReader _in = new BufferedReader(new InputStreamReader(_socket.getInputStream())); //Get the input stream, for getting message from server to client.
			PrintWriter _out = new PrintWriter(_socket.getOutputStream(), true); //Get the output stream, for sending message from client to server.
			BufferedReader _usrInput = new BufferedReader(new InputStreamReader(System.in)); //Prepare the input stream for reading user input from standard input stream

			System.out.println("Type something or press Ctrl-C to end");
			while((_str = _usrInput.readLine()) != null){ //read the user input
				_out.println(_str);//send out to server
				System.out.println(_in.readLine()); //get the message from server and print it out
			}
			
			/* Close all those socket and stream stuffs */
			_usrInput.close();
			_in.close();
			_out.close();
			_socket.close();
			System.out.println("Connection end");
		}catch(IOException _ioe){
			_ioe.printStackTrace();
		}catch(Exception _e){
			_e.printStackTrace();
		}
	}
}