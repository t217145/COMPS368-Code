package com.cyrus822.demo.springbasic.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cyrus822.demo.springbasic.entities.MyContacts;

public interface MyContactsRepo extends JpaRepository<MyContacts, Integer> {

}
