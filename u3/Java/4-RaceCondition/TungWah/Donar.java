import java.net.*;
import java.io.*;

public class Donar{
	
	public static void main(String args[]){
		int money = 0;
		int no_of_donar = 0;
		try{
			/*BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			while(true){
				System.out.print("Number of donar : ");
				String _no = br.readLine();
				try{
					money = Integer.parseInt(_no);
					if(money < 0 || money > 100){
						System.out.println("Range from 1 to 100!");
						continue;
					}
					break;
				}catch(Exception e){
					System.out.println("Please input a valid number!");
				}
			}*/
			
			no_of_donar = Integer.parseInt(args[0]);
			money = Integer.parseInt(args[1]);
			
			for(int i=0;i<no_of_donar;i++){
				new Thread(new DonarThread(money)).start();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}

class DonarThread implements Runnable{
	
	private int money;
	
	public DonarThread(int _money){
		this.money = _money;
	}
	
	public void run(){
		try{
			Socket skt = new Socket("localhost",12345);
			PrintWriter _out = new PrintWriter(skt.getOutputStream(), true);
			
			int total = 0;
			
			for(int i=0;i<money;i++){
				_out.println("1");
				total++;
			}
			
			_out.close();
			skt.close();
			
			System.out.println("Donated : " + total);			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}