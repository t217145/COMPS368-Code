package comps368.u9;

import java.util.Date;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.jms.MessageListener;

@MessageDriven(mappedName = "jms/COMPS368Topic")
public class ChatlogMDB implements MessageListener{
    @PersistenceContext(unitName="u9demo-MDBwithJPAPU")
    private EntityManager _em;

    @Override
    public void onMessage(Message _message) {
        try{
            String _msg = _message.getBody(String.class);
            
            Chatlog _l = new Chatlog();
            _l.setMessage(_msg);
            _l.setCreateDtm(new Date());
            _em.persist(_l);

            System.out.println(_msg);
        }catch(JMSException _e){
            System.out.println("Error : " + _e.getMessage());
        }
    }
}
