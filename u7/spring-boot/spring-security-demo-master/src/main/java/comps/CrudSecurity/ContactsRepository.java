package comps.CrudSecurity;

import javax.annotation.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

@Resource
public interface ContactsRepository extends JpaRepository<Contacts, Integer>{

}