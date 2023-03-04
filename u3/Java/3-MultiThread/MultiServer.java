import java.io.*;
import java.net.*;

public class MultiServer{
	
	public static void main(String args[]) throws Exception{
		ServerSocket _ss = new ServerSocket(12345);
		
		while(true){
			Socket _s = _ss.accept();
			ClientHandler _h = new ClientHandler(_s);//3a. The Runnable/Thread class
			Thread _t = new Thread(_h);//3b. Wrapper
			_t.start();//3c. start it!
		}		
	}
	
}

class ClientHandler implements Runnable{ //1. implements Runnable / extends Thread
	
	private Socket _s;
	
	public ClientHandler(Socket s){
		this._s = s;
	}
	
	public void run(){ //2. overwrite run() method
		try{
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
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}