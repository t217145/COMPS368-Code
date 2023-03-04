import java.io.*;
import java.net.*;

public class MultiClient{
//public class MonoClient{
	
	public static void main(String args[]){
		try{
			Socket _s = new Socket("localhost", 12345);
			BufferedReader _br = new BufferedReader(new InputStreamReader(System.in));
			BufferedReader _in = new BufferedReader(new InputStreamReader(_s.getInputStream()));
			PrintWriter _out = new PrintWriter(_s.getOutputStream(), true);
			String _str = "";
			
			System.out.println("Connected to server, write something. Press Ctrl-C to end");
			while((_str = _br.readLine()) != null){
				_out.println(_str);
				System.out.println(_in.readLine());
			}
			
			_out.close();
			_in.close();
			_br.close();
			_s.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}