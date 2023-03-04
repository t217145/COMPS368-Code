import java.io.*;
import java.net.*;

public class EchoServer{
	
    public static void main(String args[]){
        try{
            /* Variable definition */
            String _str;
            Socket _socket;
            BufferedReader _in;
            PrintWriter _out;
            ServerSocket _server = new ServerSocket(12345); //prepare the TCP server socket

            while(true){
                System.out.println("Waiting for connection");
                _socket = _server.accept();//waiting for a client to connect with

                System.out.println("Connection in");
                _str = "";
                _out = new PrintWriter(_socket.getOutputStream(), true); //Get the output stream from socket, for sending message from server to client
                _in = new BufferedReader(new InputStreamReader(_socket.getInputStream()));//Get the output stream from socket, for getting message from client to server

                while((_str = _in.readLine()) != null){ //get the message from client by reading line from the input stream
                    if(_str.isEmpty()){
                        break;
                    }
                    System.out.println(_str);
                    _out.println("Echo : " + _str); // send back the message to client, but add some words so that we can show that the message is really send from server
                }//end the conversation when server get a null input from client

                /* Close all in/output stream and socket */
                _in.close();
                _out.close();
                _socket.close();
                System.out.println("Connection end");
            }// continue to wait another client to join
        }catch(IOException _ioe){
            _ioe.printStackTrace();
        }catch(Exception _e){
            _e.printStackTrace();
        }
    }
	
}