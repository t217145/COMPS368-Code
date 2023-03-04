import java.io.*;
import java.net.*;
import java.util.*;

public class DemoUDPServer{
	
	public static void main(String args[]){
		try{
			String _str = "";
			InetAddress _addr = null;
			DatagramSocket _socket = new DatagramSocket(12345); //create the datagram socket
			byte[] _buffer = new byte[255];//prepare buffer to feed into incoming datagram packet
			DatagramPacket _packet = new DatagramPacket(_buffer, _buffer.length);//create the incoming datagram packet for getting client message
			byte[] _outBuffer = new byte[255];//prepare buffer to feed into outgoing datagram packet
			DatagramPacket _outPacket = new DatagramPacket(_outBuffer, _outBuffer.length);//create the outgoing datagram packet for send message to client

			while(true){
				_str = "";		
				Arrays.fill(_buffer, (byte)0); //reset buffer for each receive
				System.out.println("Waiting for connection in");//wait for connection in
				_socket.receive(_packet);//get the data from datagram packet through socket
				System.out.printf("Connection with %s created%n", _packet.getAddress());
				System.out.println("********** Message from sender **********");
				_str = new String(_buffer);
				System.out.println(_str);//process it

				_str = "Echo : " + _str; //add some words to proof that the message is really send from server
				
				Arrays.fill(_outBuffer, (byte)0); //reset buffer for each receive
				_outPacket.setSocketAddress(_packet.getSocketAddress());//set the client address, although in this demo is always localhost, but how about the server serve client from any PC?
				_outPacket.setData(_str.getBytes()); //set the message to outgoing packet
				_socket.send(_outPacket);//send back to client
			}
		}catch(IOException ioe){
			ioe.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}