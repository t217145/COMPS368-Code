/*
 * This is the very simple example of TCP socket programming
 * Send a hard coded message to receiver
 * The aims is to illustrate the core part of socket programming
 */

/*
    ************** Logic flow **************
    1. Create Socket
    2. Get Output Stream from Socket
    3. Write somethig to Output Stream
    4. Close Stream and then close socket
*/

package tcpdemo01;

import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author cyrus_kc_cheng
 */
public class Sender {
    final static String VARS = "demo02";
    
    public static void main(String args[]){
        /* 
           For simple, try catch all exception, but don't do this in real
           production apps.
           Errr......... in fact I do so in real production apps~~~
           :) :) :) :) :) 
        */
        try{
            //prepare the message
            String msg = "This is a hard coded message : [" + VARS + "]";
            /* 1. Create the Socket and fill in the receiver information */
            Socket _socket = new Socket("localhost", 12345);
            /* 2. Get the Output Stream from the socket */
            PrintWriter _out = new PrintWriter(_socket.getOutputStream(), true);
            /* 3. Wrtie the message in Output Stream */
            _out.write(msg);
            /* 4. Flush the Stream and Close the Stream and Socket */
            _out.flush();
            _out.close();
            _socket.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}