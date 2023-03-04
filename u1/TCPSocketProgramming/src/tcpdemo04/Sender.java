/*
 * Same as demo03
 */

/*
    ************** Logic flow **************
    1. Create the Socket and fill in the receiver information
    2. Get the output / input stream from the socket
    3. Continuously read user input. Leave the loop if user enter blank
    4. Wrtie the message in output stream
    5. Get the echo from receiver and print out to screen
    6. Close the Stream and Socket
*/

package tcpdemo04;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author cyrus_kc_cheng
 */
public class Sender {
    public static void main(String args[]){
        try{            
            /* 1. Create the Socket and fill in the receiver information */
            Socket _socket = new Socket("localhost", 12345);
            /* 2. Get the output / input stream from the socket */
            PrintWriter _out = new PrintWriter(_socket.getOutputStream(), true);    
            BufferedReader _in = new BufferedReader(new InputStreamReader(System.in));
            /* 3. Continuously read user input */
            String _msg = "";
            while((_msg = _in.readLine()) != null){
                if(_msg.equals("")){ /* Leave the loop if user enter blank */
                    break;
                }
                /* 4. Wrtie the message in output stream */
                _out.println(_msg);
                _out.flush(); // Flush the output stream   
                /* 5. Get the echo from receiver and print out to screen */
                _msg = _in.readLine();
                System.out.println(_msg);
            }
            /* 6. Close the Stream and Socket */
            _out.close();
            _socket.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}