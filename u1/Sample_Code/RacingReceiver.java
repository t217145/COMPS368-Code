/*
	Author 			: Cyrus Cheng
	Description :	illustration the Racing Condition of multi-threading programming 
								In normal situation, if will add 300 and then deduct 300 to zero,
								i.e. 	x = 0; 
											x = x + 1; //call 300 times
											x = x - 1; //call 300 times
								The result should always be 0
								As mentioned in today tutorial, due to the operation in CPU level,
								the + or - operation is done by load the value from stack to register
								and perform the +/- in register. 
								But in multi-thread situation, the thread (A) will read the value from stack
								while another thread (B) may processing the value at the same time. The value
								may already renewed by not yet write back to the stack. So Thread A may read 
								an outdated value and process on it.
								Moreover, even though thread B get the latest value, but if thread A not yet 
								write the updated value to the stack. After Thread B write the most updated
								and correct value back to stack, but thread A overwrite the outdated value to
								stack, as a result, the final value is also incorrect.
	
	******************   IMPORTANT   ******************
	Usually, you will not be asked to write such code,
	but you may asked to implement the solution to solve
	racing condition issue. Therefore, no need to memorize
	the code here, but just a brief understand and locate
	where is the point require to apply "control" -
	synchronization
	******************   IMPORTANT   ******************
	
	Logic				:	The "main" and those method in CountObj is simple, I won't go into detail
								The logic of "run"
								1. Prepare the buffered reader to read client message
								2. The 1st message get from client is an indicator to indicate whether the
									 sender is performing "plus" or "mins" operation
								3. A while the read message from client endlessly.
									a. there are 2 type of message from client. 1 is "cls", this will reset the common "CountObj" current count to zero
									b. any message other than "cls". This will perform either "add" or "mins" depends on the current thread is a "plus"
										 or "mins" operator
								
								4. For plus operator, it will call the add method of count object 300 times, add 1 to the counter each time. But after
								   each add operation, sleep for 1/100 second or else it will very hard to let the racing condition appear.
								
								5. For mins operator, it will call the mins method of count object 300 times, deduct 1 to the counter each time. But 
								   after each mins operation, sleep for 1/100 second or else it will very hard to let the racing condition appear.
*/

import java.io.*;
import java.net.*;
import java.lang.*;

public class RacingReceiver{

    public static void main(String args[]){
        try{
						CountObj _obj = new CountObj(); /* Common "Data" that shared by plus and mins thread */
            ServerSocket _server = new ServerSocket(12345);

						System.out.println("Waiting for connection");
            for(int i=0;i<2;i++){
							new Thread(new Receiverhandler( _server.accept(), _obj)).start(); //pass the parameter to thread object and start it
            }
        }catch(Exception _e){
            _e.printStackTrace();
        }
    }

}

class CountObj{
	private int _currentCnt = 0;
	
	public void add(){ /* add 1 to the current count when this method called */
		this._currentCnt = this._currentCnt + 1;
	}
	
	public void mins(){ /* mins 1 to the current count when this method called */
		this._currentCnt = this._currentCnt - 1;
	}	
	
	public void resetCnt(){ /* reset the current count to 0 when this method called */
		this._currentCnt = 0;
	}	
	
	public int getCnt(){ /* return the current count to caller when this method called */
		return this._currentCnt;
	}
}

class Receiverhandler implements Runnable{

		private String _str = "", _side = "";  
    private Socket _s = null;
    private BufferedReader _br = null;
		private CountObj _obj = null;
		private final int _COUNTER = 300;

    public Receiverhandler(Socket s, CountObj obj){
        this._s = s;
				this._obj = obj;
    }

    public void run(){
        try{
            _br = new BufferedReader(new InputStreamReader(_s.getInputStream()));
						_side = _br.readLine();//1st argument is + or -
						System.out.println("Client {" + (_side.equals("p") ? "plus" : "mins") + "} connected");
						
            while((_str = _br.readLine()) != null){
							/* If sender send a message "cls", it will reset the count to zero and wait for next instruction */
							if(_str.equals("cls")){
								_obj.resetCnt();
								System.out.println("Reset object cnt : {" + Integer.toString(_obj.getCnt()) + "}");
								continue;/*********** will not further process the subsequent code, back to readLine() to wait for next instruction ***********/
							}
							
							System.out.println((_side.equals("p") ? "+" : "-") + _COUNTER + " to the count");
							/* If this thread is a "plus" operator, call the "add" method of counter object 300 times so that similar to add 300 to current counter */
							if(_side.equals("p")){
								for(int i=0;i<_COUNTER;i++){
									Thread.sleep(10);
									_obj.add();
								}
								System.out.println("Current object cnt : {" + Integer.toString(_obj.getCnt()) + "}");
							} else {	
							/* If this thread is a "mins" operator, call the "mins" method of counter object 300 times so that similar to deduct 300 to current counter */
								for(int i=0;i<_COUNTER;i++){
									Thread.sleep(10);
									_obj.mins();
								}
								System.out.println("Current object cnt : {" + Integer.toString(_obj.getCnt()) + "}");
							}
						}
            _br.close();
            _s.close();
        }catch(InterruptedException _ie){
            _ie.printStackTrace();
        }catch(IOException _ioe){
            _ioe.printStackTrace();
        }
    }

}