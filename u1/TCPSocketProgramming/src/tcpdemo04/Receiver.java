/*
 * On top of demo03, this receiver can handle concurrent sender and print out
 * message from multiple sender concurrently
 */

/*
    ************** Logic flow **************
    1. Create a ServerSocket
    2. Listen to a port by ServerSocket. 
    3. When a request reach that port, create socket object to establish the connection
    4. Create a Sender Handler object and wrap in the Thread class for multi-thread handling
    5. Get the input / output stream from socket object
    6. Continously read the message from input stream
    7. Echo back the message to output stream
    8. Close all stream and then close the socket + Server socket
*/

package tcpdemo04;

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
        int cnt = 0;
        try{
            //1. Create a ServerSocket
            ServerSocket _svr = new ServerSocket(12345);
            Socket _socket = null;
            //2. Listen to a port by ServerSocket.
            //3. When a request reach that port, create socket object to establish the connection
            while((_socket = _svr.accept()) != null){
                //4. Create a Sender Handler object and wrap in the Thread class for multi-thread handling
                new Thread(new SenderHandler(++cnt, _socket)).start();
            }
            _svr.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}

class SenderHandler implements Runnable{
    
    private Socket _socket;
    private String _clinetName;
    
    public SenderHandler(int cnt, Socket _s){
        this._socket = _s;
        this._clinetName = "Client " + Integer.toString(cnt);
    }
    
    public void run(){
        try{
            //5. Get the input / output stream from socket object
            BufferedReader _in = new BufferedReader(
                                    new InputStreamReader(
                                            this._socket.getInputStream()
                                    )
                                );
            PrintWriter _out = new PrintWriter(this._socket.getOutputStream(), true);
            //6. Continously read the message from input stream
            String _msg = "";
            while((_msg = _in.readLine()) != null){
                System.out.println("Message from [" + _clinetName + "] : " + _msg);
                //7. Echo back the message to output stream
                _out.println("Message received : " + _msg);
                _out.flush();                    
            }
            //8. Close all stream and then close the socket + Server socket
            _out.close();
            _in.close();
            this._socket.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}