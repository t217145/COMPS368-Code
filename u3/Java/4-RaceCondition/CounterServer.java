import java.io.*;
import java.util.concurrent.locks.ReentrantLock;

public class CounterServer{
	
	private static final int _THREAD_COUNT = 5;
	private static final int _THREAD_VALUE = 1000;
	private static boolean _useSync = false;
	private static boolean _useLock = false;
	
	public static void main(String args[]) throws Exception{
		//get the preferences
		BufferedReader _br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Choose a Thread safe mechanism\r\n\t(S) : Use Synchronized\r\n\t(L) : Use Lock\r\n\t(N) : No thread safe mechanism");
		String _str = _br.readLine();
		if (_str.equals("S")) {
			_useSync = true;
		} else if (_str.equals("L")) {
			_useLock = true;
		}
			
		SharedObject _obj = _useLock ? new SharedLockObject() : new SharedObject();
		System.out.println("InitValue\tThis value\tCurrent value");
		Thread[] _tPool = new Thread[_THREAD_COUNT];
		
		for(int i=0;i<_THREAD_COUNT;i++){
			//3. instantize and start the thread
			CounterHandler _h = new CounterHandler(_obj, _THREAD_VALUE, _useSync);
			_tPool[i] = new Thread(_h);
			_tPool[i].start();
		}
		
		//Wait for all thread to end
		for(Thread _t : _tPool){
			_t.join();
		}
		
		System.out.println("Thread Count x value = final value");
		System.out.println(_THREAD_COUNT + " x " + _THREAD_VALUE + " = " + _obj.getValue());					
	}
}

class CounterHandler implements Runnable{ //2. implements Runnable
	
	private boolean _useSync = false;
	private int _value, _initValue;
	private SharedObject _obj;
	
	public CounterHandler(SharedObject obj, int value, boolean useSync){
		this._obj = obj;
		this._value = value;
		this._useSync = useSync;
		this._initValue = this._obj.getValue();
	}
	
	public void run(){ //3. overwrite the run() method
		if(this._useSync){
			synchronized(_obj){
				for(int i=0;i<this._value;i++){
					_obj.addOne();
				}
				System.out.println(this._initValue + "\t\t" + this._value + "\t\t" + this._obj.getValue());
				_obj.finished();
			}			
		}else{
			for(int i=0;i<this._value;i++){
				_obj.addOne();
			}
			System.out.println(this._initValue + "\t\t" + this._value + "\t\t" + this._obj.getValue());
			_obj.finished();		
		}
	}
}

class SharedObject{
	
	protected int count = 0;
	private int finishedCnt = 0;

	public /*synchronized*/ void addOne(){
		this.add(1);
	}
	
	public /*synchronized*/ void add(int value){
		this.count += value;
	}
	
	public int getValue(){
		return this.count;
	}
	
	public void finished(){
		this.finishedCnt++;
	}
	
	public int getFinishedCnt(){
		return this.finishedCnt;
	}
}

class SharedLockObject extends SharedObject{
	private ReentrantLock _lock = new ReentrantLock();

	//critical region
	public void addOne(){
		_lock.lock();
		super.add(1);
		_lock.unlock();
	}
	
	//critical region
	public void add(int value){
		_lock.lock();
		super.count += value;
		_lock.unlock();
	}
}