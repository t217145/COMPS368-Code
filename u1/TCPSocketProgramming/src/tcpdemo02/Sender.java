/*
 * On top of demo01, this sender can receive echo from receiver
 */

/*
    ************** Logic flow **************
    1. Create the Socket and fill in the receiver information
    2. Get the output / input Stream from the socket
    3. Wrtie the message in Output Stream
    4. Get the echo back from server and print it to screen
    5. Close the Stream and Socket
*/

package tcpdemo02;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author cyrus_kc_cheng
 */
public class Sender {
    final static String VARS = "demo02";
    
    public static void main(String args[]){
        try{
            // prepare the message
            String msg = "This is a hard coded message : [" + VARS + "]";
            /* 1. Create the Socket and fill in the receiver information */
            Socket _socket = new Socket("localhost", 12345);
            /* 2. Get the output / input Stream from the socket */
            PrintWriter _out = new PrintWriter(_socket.getOutputStream(), true);            
            BufferedReader _in = new BufferedReader(
                                    new InputStreamReader(
                                            _socket.getInputStream()
                                    )
                                );
            /* 3. Wrtie the message in Output Stream */
            _out.println(msg);
            _out.flush();
            /* 4. Get the echo back from server and print it to screen */
            System.out.println("Message from server : " + _in.readLine());
            /* 5. Close the Stream and Socket */
            _in.close();
            _out.close();
            _socket.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}