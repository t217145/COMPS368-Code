package comps368.u4.cruddemo;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class App01 implements CommandLineRunner{

    @Autowired
    private DemoRepo repo;

    @Override
    public void run(String... args) throws Exception {
        //c
        DemoModel newModel = new DemoModel(0, "Cyrus", "55443544", null);
        repo.save(newModel);


        Optional<DemoModel> dbModelOptional = repo.findById(1);
        if(dbModelOptional.isPresent()){
            //u
            DemoModel dbModel = dbModelOptional.get();
            dbModel.setEmail("cyrus@cyrus-sir.com");
            repo.save(dbModel);

            //r
            Optional<DemoModel> dbModelOptionalAgain = repo.findById(1);
            DemoModel dbModel2 = dbModelOptionalAgain.get();
            System.out.println(dbModel2.toString());
        }

        //for you to interact in web console http://localhost:8080/h2-console
        System.in.read();

        List<DemoModel> allModels = repo.findAll();
        for (DemoModel demoModel : allModels) {
            System.out.println(demoModel.toString());
        }

        //d
        repo.deleteById(1);
        System.out.println("Record deleted");

        System.in.read();
    }
    
}
