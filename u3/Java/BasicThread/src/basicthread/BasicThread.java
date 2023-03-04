package basicthread;

public class BasicThread {

    public static void main(String[] args) {
       //create 10 Dummy
       for(int i=0;i<10;i++){
           Dummy d = new Dummy(i+1);
           d.call();
       }

       //see the effect of DummyThread
//       for(int i=0;i<10;i++){
//           DummyThread d = new DummyThread(i+1);
//           d.start();
//       }

       //see the effect of DummyRunnable
//       for(int i=0;i<10;i++){
//           new Thread(new DummyRunnable(i+1)).start();
//       }
    }
    
}

class Dummy extends Thread{

    private int _id;
    
    public Dummy(int id){
        this._id = id;
    }

    public void call(){
        for(int i=0;i<10;i++){
            System.out.println("I am " + _id);
        }
    }
}

class DummyThread extends Thread{

    private int _id;
    
    public DummyThread(int id){
        this._id = id;
    }

    @Override
    public void run(){
        for(int i=0;i<10;i++){
            System.out.println("I am " + _id);
        }
    }
}

class DummyRunnable implements Runnable{

    private int _id;
    
    public DummyRunnable(int id){
        this._id = id;
    }

    @Override
    public void run(){
        for(int i=0;i<10;i++){
            System.out.println("I am " + _id);
        }
    }
}