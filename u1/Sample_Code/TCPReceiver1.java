import java.io.*;
import java.net.*;

public class TCPReceiver1{

	public static void main(String args[]){
		try{
			/* 1. Create a Server Socket */
			ServerSocket _svr = new ServerSocket(12345);
			/* 2. Listen to the socket, and wait for connection */
			/* 3. Create Socket object when connection in */
			Socket _skt = _svr.accept();
			/* 4. Get the input stream from Socket object */
			BufferedReader _br = new BufferedReader(new InputStreamReader(_skt.getInputStream()));
			/* 5. Get the message from the stream, and print it out */
			String _msg = _br.readLine();
			System.out.println(_msg);
			/* 6. Close the InputStream, Socket and Server Socket in sequence */
			_br.close();
			_skt.close();
			_svr.close();
		}catch(IOException _ioe){
			_ioe.printStackTrace();
		}catch(Exception _e){
			_e.printStackTrace();
		}
	}

}