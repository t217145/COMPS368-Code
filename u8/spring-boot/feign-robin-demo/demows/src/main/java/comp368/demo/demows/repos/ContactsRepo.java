package comp368.demo.demows.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import comp368.demo.demows.models.Contacts;

public interface ContactsRepo extends JpaRepository<Contacts, Integer> {
    
}
