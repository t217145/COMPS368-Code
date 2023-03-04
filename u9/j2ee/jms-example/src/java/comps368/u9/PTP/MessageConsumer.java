/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comps368.u9.PTP;

import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.Queue;

/**
 *
 * @author Cyrus Cheng
 */
public class MessageConsumer {

    @Resource(lookup="jms/COMPS368ConnectionFactory")
    private static ConnectionFactory _f;
    
    @Resource(lookup="jms/COMPS368Queue")
    private static Queue _q;

    public static void main(String[] args) {
        try(JMSContext ctx = _f.createContext()){
            JMSConsumer _c = ctx.createConsumer(_q);
            System.out.println(_c.receiveBody(String.class, 10000));
        }
    }
    
}