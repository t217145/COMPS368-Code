package udpdemo01;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 *
 * @author Cyrus Cheng
 */
public class DemoUDPReceiver{
	
    public static void main(String args[]){
        try{
            DatagramSocket _socket = new DatagramSocket(12345); //create the datagram socket

            byte[] _buffer = new byte[255];//prepare buffer to feed into datagram packet
            DatagramPacket _packet = new DatagramPacket(_buffer, _buffer.length);//create the datagram packet
            System.out.println("Waiting for connection in");//wait for connection in

            while(true){
                Arrays.fill(_buffer, (byte)0); //reset buffer for each receive
                _socket.receive(_packet);//get the data from datagram packet through socket
                System.out.printf("Connection with %s created%n", _packet.getAddress());
                System.out.println("********** Message from sender **********");
                System.out.println(new String(_buffer));//process it
            }
        }catch(IOException ioe){
            ioe.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
	
}
