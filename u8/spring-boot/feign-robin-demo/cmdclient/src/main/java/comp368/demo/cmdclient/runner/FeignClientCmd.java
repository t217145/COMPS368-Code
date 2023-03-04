package comp368.demo.cmdclient.runner;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import comp368.demo.cmdclient.feignapi.demowsapi;
import comp368.demo.cmdclient.models.Contacts;

@Component
@ConditionalOnProperty(
  value="client.type", 
  havingValue = "feign", 
  matchIfMissing = true)
public class FeignClientCmd implements CommandLineRunner {

    @Autowired
    private demowsapi api;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("\r\n\r\n\r\n\r\n/************Running Feign Client ************/\r\n\r\n\r\n\r\n");
        try{
            api.add(new Contacts(0, "From Feign", null, "Testing"));

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            while(true){
                List<Contacts> allContacts = api.index();
                for (Contacts contacts : allContacts) {
                    System.out.println(contacts.toString());
                }
                System.out.println("Press any key to run again\t\tPress \"X\" to exit.");
                String input = "";
                if((input = br.readLine()) != null && input.equals("X")){
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }//end of try-catch
    }//end of run()
    
}