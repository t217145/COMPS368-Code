package com.cyrus822.demo.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cyrus822.demo.entities.MyContacts;

public interface MyContactsRepo extends JpaRepository<MyContacts, Integer> {

}
