package comp368.demo.cmdclient.runner;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import comp368.demo.cmdclient.models.Contacts;

@Component
@ConditionalOnProperty(
  value="client.type", 
  havingValue = "rest", 
  matchIfMissing = false)
public class RTClientCmd implements CommandLineRunner {

    @Value("${ws.url}")
    private String url;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("\r\n\r\n\r\n\r\n/************Running Feign Client ************/\r\n\r\n\r\n\r\n");
        try{
            RestTemplate rt = new RestTemplate();

            //Prepare header
            HttpHeaders headers = new HttpHeaders();
            List<MediaType> list = new ArrayList<>();
            list.add(MediaType.APPLICATION_JSON);
            headers.setAccept(list);
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Content-Type", "application/json");

            //Prepare the object
            Contacts newContacts = new Contacts(0, "Dummy", "12345678", null);

            //Prepare the call
            HttpEntity<Contacts> entry = new HttpEntity<>(newContacts, headers);
            ResponseEntity<Contacts> result = rt.exchange(url, HttpMethod.PUT, entry, Contacts.class);
            Contacts rtn = result.getBody();

            //Print it out
            System.out.println(rtn == null ? "Return null" : rtn.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }//end of try-catch
    }//end of run()
    
}