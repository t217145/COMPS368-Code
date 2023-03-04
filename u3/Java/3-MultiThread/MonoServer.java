import java.io.*;
import java.net.*;

public class MultiServer{
	
	public static void main(String args[]) throws Exception{
		ServerSocket _ss = new ServerSocket(12345);
		
		while(true){
			Socket _s = _ss.accept();
			BufferedReader _in = new BufferedReader(new InputStreamReader(_s.getInputStream()));
			PrintWriter _out = new PrintWriter(_s.getOutputStream(), true);
			String _str = "";
			
			while((_str = _in.readLine()) != null){
				System.out.println("From Client : " + _str);
				_out.println("Echo : " + _str);
			}
			
			_out.close();
			_in.close();
			_s.close();
		}		
	}
	
}