import java.io.*;
import java.net.*;

public class RacingSender{

    public static void main(String args[]) throws Exception{
				final int _COUNTER = 300;
        String _str = "";
        Socket _s = new Socket("localhost", 12345);

        BufferedReader _stdIn = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter _out = new PrintWriter(_s.getOutputStream(), true);
				
				/*
					get the argument from command, 
						"p" stand for this sender call the "add" method in receiver's counter object
						"m", empty value or other value stand for this sender call the "mins" method in receiver's counter object
				*/
				if(args.length > 0 && args[0].equals("p")){ 
					_out.println("p");
				} else {
					_out.println("m");
				}
				System.out.println("***************" + (args[0].equals("p") ? "Plus" : "Mins") + " " + _COUNTER + " ***************");

				/* get user input and send to receiver */
				while((_str = _stdIn.readLine()) != null){
					_out.println(_str);
					_out.flush();
				}
				
        _out.close();
        _stdIn.close();
        _s.close();
    }

}