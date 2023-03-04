import java.io.*;
import java.net.*;
import java.util.Random;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class TamsShop{
	
	private static final int _CAPACITY = 5;
	
	public static void main(String args[]){
		/* Preparing */
		//Act as the order queue
		LinkedList<Integer> _queue = new LinkedList<Integer>();
		//Cars is wating at first
		Semaphore _cars = new Semaphore(_CAPACITY);
		//Noodle should not be cooked at first
		Semaphore _cookedNoodles = new Semaphore(0);
		
		/* Start the business */
		//5 cars
		for(int i=0; i < _CAPACITY*2; i++){
			new Thread(new FoodPanda(_cars, _cookedNoodles, _queue)).start();
		}		
		//5 kitchen
		for(int i=0; i < _CAPACITY*3; i++){
			new Thread(new TamsKitchen(_cars, _cookedNoodles, _queue)).start();
		}
	}
	
}

class FoodPanda implements Runnable{
	
	private Random _rand;
	private Semaphore _cars, _cookedNoodles;
	private LinkedList<Integer> _queue;
	private int _noodleCtr;
	private String _str1, _str2;
	
	
	public FoodPanda(Semaphore cars, Semaphore cookedNoodles, LinkedList<Integer> queue){
		this._cars = cars;
		this._cookedNoodles = cookedNoodles;
		this._queue = queue;
		this._rand = new Random(System.currentTimeMillis());
	}
	
	public void run(){
		try{
			while(true){
				if(_cookedNoodles.hasQueuedThreads()){
					System.out.println("\t\t\t\t\t\t\t\t\t\tWaiting for noodle.....");
				}				
				_cookedNoodles.acquire();/* Consume a noodle */
				
				/* Start - Prepare the information to print out */
				_queue.
				_noodleCtr = _queue.removeFirst();
				_str1 = "\t\t\t\t\t\t\t\t\t\tStart sending {" + _noodleCtr + "} noodles to client.....";
				_str2 = "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tFinish sending {" + _noodleCtr + "} noodles to client.";
				_rand = new Random(System.currentTimeMillis());
				/* End - Prepare the information to print out */	
				
				/* Start - Do the stuff (i.e. print out in this demo) */
				System.out.println(_str1);
				Thread.sleep((_rand.nextInt(8) + 1) * 1000);
				System.out.println(_str2);
				/* End - Do the stuff (i.e. print out in this demo) */
				
				_cars.release();/* Return a car */
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}

class TamsKitchen implements Runnable{
	
	private Random _rand;
	private Semaphore _cars, _cookedNoodles;
	private LinkedList<Integer> _queue;
	private static int _noodleCtr;
	private String _str1, _str2;
	
	public TamsKitchen(Semaphore cars, Semaphore cookedNoodles, LinkedList<Integer> queue){
		_noodleCtr = 0;
		this._cars = cars;
		this._cookedNoodles = cookedNoodles;
		this._queue = queue;
		this._rand = new Random(System.currentTimeMillis());
	}	

	public void run(){
		try{
			while(true){
				if(_cars.hasQueuedThreads()){
					System.out.println("Waiting for car.......");
				}
				_cars.acquire();/* Consume a car */
				/* Start - Prepare the information to print out */
				_noodleCtr++;
				_str1 = "Start cooking {" + _noodleCtr + "} noodles.....";
				_str2 = "\t\t\t\t\tFinish cooking {" + _noodleCtr + "} noodles.";
				_queue.add(_noodleCtr);
				_rand = new Random(System.currentTimeMillis());
				/* End - Prepare the information to print out */
				/* Start - Do the stuff (i.e. print out in this demo) */
				System.out.println(_str1);
				Thread.sleep((_rand.nextInt(3) + 1) * 1000);
				System.out.println(_str2);
				/* End - Do the stuff (i.e. print out in this demo) */
				_cookedNoodles.release();/* Produce a noodle */
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}	