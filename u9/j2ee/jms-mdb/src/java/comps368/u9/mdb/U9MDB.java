/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comps368.u9.mdb;

import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 *
 * @author Cyrus Cheng
 */
@MessageDriven(mappedName = "jms/COMPS368Topic")
public class U9MDB implements MessageListener {

    @Override
    public void onMessage(Message message) {
        try{
            System.out.println("Logged from MDB : " 
                    + message.getBody(String.class));
        }catch(JMSException _e){
            System.out.println("Error : " + _e.getMessage());
        }        
    }
    
}
