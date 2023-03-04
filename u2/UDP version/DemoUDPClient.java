import java.io.*;
import java.net.*;
import java.util.*;

public class DemoUDPClient{
	
	public static void main(String args[]){
		try{
			DatagramSocket _socket = new DatagramSocket(); //create the datagram socket
			
			byte[] _buffer = new byte[255];//prepare buffer to feed into datagram packet
			InetAddress _addr = InetAddress.getByAddress(new byte[]{127, 0, 0, 1});//prepare address to feed into datagram packet
			DatagramPacket _packet = null;//create the datagram packet
			
			BufferedReader _br = new BufferedReader(new InputStreamReader(System.in)); //prepare for collecting user input
			String _str = "";
			System.out.println("Type something and then press [Enter] to send");
			while((_str = _br.readLine()) != null){//get the user input
				Arrays.fill(_buffer, (byte)0); //reset buffer for each send out
				_packet = new DatagramPacket(_buffer, _buffer.length, _addr, 12345);
				_packet.setData(_str.getBytes());//feed the datagram with data in byte array format
				_socket.send(_packet);//send out the packet through socket

				_packet = new DatagramPacket(_buffer, _buffer.length);
				_socket.receive(_packet);//get the server feedback
				System.out.println(new String(_packet.getData()));
			}
		}catch(IOException ioe){
			ioe.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}		
	}
	
}