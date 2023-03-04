package comps368.u9;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Topic;

public class ChatApps {
    /* Lookup the JMS admin object - connection factory - in GlassFish server */
    @Resource(lookup="jms/COMPS368ConnectionFactory")
    private static  ConnectionFactory _cf;
    
    /* Lookup the JMS admin object - Queue - in GlassFish server */
    @Resource(lookup="jms/COMPS368Topic")
    private static Topic _t;
    
    public static void main(String[] args) throws Exception {
        try(JMSContext _ctx = _cf.createContext()){
            /* 1. First subscribe a topic with a listener */
            JMSConsumer _consumer = _ctx.createConsumer(_t);
            _consumer.setMessageListener(new AsyncListener());
            
            /* 2. Create producer and get user input and then send to Topic*/
            JMSProducer _producer = _ctx.createProducer();
            
            String input = "";
            BufferedReader _br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Please input some message : ");
            while((input = _br.readLine()) != null){
                _producer.send(_t, input);
            }
        }//end of JMSContext
    }
    
}

class AsyncListener implements MessageListener{
    @Override
    public void onMessage(Message _msg) {
        try{
            System.out.println("Message : " + _msg.getBody(String.class));
        }catch(JMSException _e){
            _e.printStackTrace();
        }
    }
}