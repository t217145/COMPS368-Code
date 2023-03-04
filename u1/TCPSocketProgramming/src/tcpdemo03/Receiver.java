/*
 * On top of demo02, this receiver can handle multiple message from sender
 */

/*
    ************** Logic flow **************
    1. Create a ServerSocket
    2. Listen to a port by ServerSocket. 
    3. When a request reach that port, create socket object to establish the connection
    4. Get the input / output stream from socket object
    5. Continously read the message from input stream
    6. Echo back the message to output stream
    7. Close all stream and then close the socket + Server socket
*/

package tcpdemo03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author cyrus_kc_cheng
 */
public class Receiver {
    public static void main(String args[]){
        try{
            //1. Create a ServerSocket
            ServerSocket _svr = new ServerSocket(12345);
            Socket _socket = null;
            //2. Listen to a port by ServerSocket.
            //3. When a request reach that port, create socket object to establish the connection
            while((_socket = _svr.accept()) != null){
                //4. Prepare the input / output stream
                BufferedReader _in = new BufferedReader(
                                        new InputStreamReader(
                                                _socket.getInputStream()
                                        )
                                    );
                //4. Get the input / output stream from socket object
                PrintWriter _out = new PrintWriter(_socket.getOutputStream(), true);
                //5. Continously read the message from input stream
                String _msg = "";
                while((_msg = _in.readLine()) != null){
                    System.out.println(_msg);
                    //6. Echo back the message to output stream
                    _out.println("Message received : " + _msg);
                    _out.flush();                    
                }
                //7. Close all stream and then close the socket + Server socket
                _out.close();
                _in.close();
                _socket.close();
            }
            _svr.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}