package com.cyrus822.u5.basicweb.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.cyrus822.u5.basicweb.models.Contacts;

public interface ContactsRepo extends JpaRepository<Contacts, Integer> {
    @Query("select c from Contacts c where c.name like :prefix%")
    List<Contacts> getByNameStartWith(String prefix);
}
