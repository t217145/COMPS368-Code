package com.cyrus822.demo.springbasic.runners;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.cyrus822.demo.springbasic.entities.MyContacts;
import com.cyrus822.demo.springbasic.repos.MyContactsRepo;

@Component
public class DemoRunner implements CommandLineRunner {

    @Autowired
    private MyContactsRepo repo;

    @Override
    public void run(String... args) throws Exception {
        // 1. Get values from console
        System.out.print("Name : ");
        String name = System.console().readLine();

        System.out.print("Phone : ");
        String phone = System.console().readLine();

        System.out.print("Email : ");
        String email = System.console().readLine();

        // 2. Create object
        MyContacts newContacts = new MyContacts(0, name, phone, email);
        repo.save(newContacts);

        // 3. Retrieve all contacts
        List<MyContacts> contacts = repo.findAll();
        contacts.stream().forEach(System.out::println);

        // 4. Retrieve single contact
        int contactId = 1;
        Optional<MyContacts> contactOptional = repo.findById(contactId);
        if (contactOptional.isPresent()) {
            MyContacts contact = contactOptional.get();
            System.out.println(contact);
        } else {
            System.out.println("Contact not found");
        }
    }

}
