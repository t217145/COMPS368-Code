import java.net.*;
import java.io.*;
import java.util.concurrent.locks.ReentrantLock;

public class TungWah{
	
	public static void main(String args[]){
		try{
			ServerSocket ssk = new ServerSocket(12345);
			DonationPool pool = new DonationPool();
			int limit = 10;
			int cnt = 0;
			DonarHandler[] ds = new DonarHandler[limit];
			while(cnt < limit){
				ds[cnt] = new DonarHandler(ssk.accept(), pool);
				System.out.println("Donar connected");				
				cnt++;
			}
			for(int i=0;i<ds.length;i++){
				ds[i].start();
			}
			for(int i=0;i<ds.length;i++){
				ds[i].join();
			}
			System.out.println("Grant Total : " + pool.getTotal());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}

class DonationPool{
	private ReentrantLock _lock = new ReentrantLock();
	private int grantTotal;
	
	public void addOne(){
		_lock.lock();
		int tmp = grantTotal + 1;
		grantTotal = tmp;
		_lock.unlock();
	}
	
	public int getTotal(){
		return this.grantTotal;
	}
}

class DonarHandler extends Thread{
	
	private Socket skt;
	private DonationPool pool;
	
	public DonarHandler(Socket _skt, DonationPool _pool){
		this.skt = _skt;
		this.pool = _pool;
	}
	
	public void run(){
		try{
			BufferedReader _br = new BufferedReader(new InputStreamReader(skt.getInputStream()));
			String input = "";
			int thisTotal = 0;
			while((input = _br.readLine()) != null){
				pool.addOne();
				thisTotal++;
			}
			_br.close();
			skt.close();
			System.out.println("This donar donated : " + thisTotal);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}