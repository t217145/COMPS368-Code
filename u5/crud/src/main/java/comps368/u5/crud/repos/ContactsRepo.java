package comps368.u5.crud.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import comps368.u5.crud.models.Contacts;
import java.util.List;
import java.util.Optional;

@Repository
public interface ContactsRepo extends JpaRepository<Contacts, Integer> {
    @Query("select c from Contacts c where c.cName = :name")
    Optional<Contacts> customFindByCName(String name);

    List<Contacts> findBycName(String cName);
}