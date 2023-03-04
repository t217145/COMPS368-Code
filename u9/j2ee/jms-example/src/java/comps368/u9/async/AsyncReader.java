/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comps368.u9.async;

import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;

/**
 *
 * @author Cyrus Cheng
 */
public class AsyncReader {
    
    @Resource(lookup="jms/COMPS368ConnectionFactory")
    private static  ConnectionFactory _f;

    @Resource(lookup="jms/COMPS368Queue")
    private static Queue _q; 
    
    public static void main(String[] args) throws Exception{
        try(JMSContext _ctx = _f.createContext()){
            JMSConsumer _consumer = _ctx.createConsumer(_q);
            _consumer.setMessageListener(new AsyncListener());
            System.out.println("Listening...");
            while(true){
                try {
                    Thread.sleep(60000);
                } catch (InterruptedException _e) {
                    _e.printStackTrace();
                }//end of try-catch InterruptedException
            }
        }//end of JMSContext
    }
}

class AsyncListener implements MessageListener{
    @Override
    public void onMessage(Message _msg) {
        try{
            System.out.println("Message from message queue : " 
                    + _msg.getBody(String.class));
        }catch(JMSException _e){
            _e.printStackTrace();
        }
    }
}
