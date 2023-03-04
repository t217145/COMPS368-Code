import java.io.*;
import java.net.*;

public class DemoTCPReceiver{
	
	public static void main(String args[]){
		try{
			String _str; //declare variable for later use
			BufferedReader _reader;
			
			ServerSocket _server = new ServerSocket(12345); //prepare the ServerSocket which will create a Socket object when there is a new connection
			
			while(true){
				_str = ""; //reset variable whenever there is a new connection
				_reader = null;
				
				System.out.println("Waiting for connection in");
				Socket _socket = _server.accept(); //Waiting...
				
				System.out.printf("Connection with %s created%n", _socket.getInetAddress().getHostAddress()); //If this code run, that's mean there is a connection in
				
				_reader = new BufferedReader(new InputStreamReader(_socket.getInputStream()));//get the input stream from the socket and create a reader that read the stream data
				
				System.out.println("********** Message from sender **********");
				while((_str = _reader.readLine()) != null){//read the data from input stream
					System.out.println(_str);//process it
				}//repeat reading
				
				_socket.close();//or close the connection
				System.out.println("Connection closed");
			}//process next sender request
		}catch(IOException ioe){
			ioe.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}