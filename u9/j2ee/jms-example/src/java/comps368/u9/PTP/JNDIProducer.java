/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comps368.u9.PTP;

import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Queue;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Cyrus Cheng
 */
public class JNDIProducer {

    private static ConnectionFactory _f;
    
    private static Queue _q;

    public static void main(String[] args) 
            throws JMSException, NamingException {
        
        InitialContext iCtx = new InitialContext();
        _f = (ConnectionFactory)iCtx.lookup("jms/COMPS368ConnectionFactory");
        _q = (Queue)iCtx.lookup("jms/COMPS368Queue");        
        
        try(JMSContext ctx = _f.createContext()){
            JMSProducer _p = ctx.createProducer();
            String msg = "Hello World";
            _p.send(_q, msg);
            System.out.println("Message Sent");
        }
    }
    
}
