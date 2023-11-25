package com.cyrus822.demo.runners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.cyrus822.demo.entities.MyContacts;
import com.cyrus822.demo.repos.MyContactsRepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

@Component
public class DemoRunner implements CommandLineRunner {

    @Autowired
    private MyContactsRepo repo;

    @Override
    public void run(String... args) throws Exception {
        try {

            // 1. insert record
            System.out.print("Name");
            String name = System.console().readLine();

            System.out.print("Phone");
            String phone = System.console().readLine();

            System.out.print("Email");
            String email = System.console().readLine();
            repo.save(new MyContacts(0, name, phone, email));

            // 2. retrieve record

        } catch (Exception e) {
            e.printStackTrace();
        } // end of try-catch
    }

}
