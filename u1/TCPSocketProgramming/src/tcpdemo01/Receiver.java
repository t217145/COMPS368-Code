/*
 * This is the very simple example of TCP socket programming
 * Send a hard coded message to receiver
 * The aims is to illustrate the core part of socket programming
 */

/*
    ************** Logic flow **************
    1. Create a ServerSocket
    2. Listen a certain port by the ServerSocket
    3. When a request reach that port, create socket object to establish the connection
    4. Get the input stream from socket object
    5. Read the message from input stream and print to screen
    6. Close the stream and then the socket + Server socket
*/

package tcpdemo01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author cyrus_kc_cheng
 */
public class Receiver {
    public static void main(String args[]){
        try{
            // 1. Create a ServerSocket
            ServerSocket _svr = new ServerSocket(12345);
            // 2. Listen a certain port by the ServerSocket
            // 3. When a request reach that port, create socket object to establish the connection
            Socket _socket = _svr.accept();
            //4. Get the input stream from socket object
            BufferedReader _in = new BufferedReader(new InputStreamReader(_socket.getInputStream()));
            //5. Read the message from input stream and print to screen
            String _msg = _in.readLine();
            System.out.println(_msg);
            //6. Close the stream and then the socket + Server socket
            _in.close();
            _socket.close();
            _svr.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}