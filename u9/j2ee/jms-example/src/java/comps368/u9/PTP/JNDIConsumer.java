/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comps368.u9.PTP;

import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Cyrus Cheng
 */
public class JNDIConsumer {
    private static  ConnectionFactory _f;
    private static Queue _q;
    
    public static void main(String[] args) 
            throws JMSException, NamingException {

        InitialContext iCtx = new InitialContext();
        _f = (ConnectionFactory)iCtx.lookup("jms/COMPS368ConnectionFactory");
        _q = (Queue)iCtx.lookup("jms/COMPS368Queue");
        
        try(JMSContext _ctx = _f.createContext()){
            JMSConsumer _c = _ctx.createConsumer(_q);
            System.out.println(_c.receiveBody(String.class, 10*1000));
        }
    }
}
