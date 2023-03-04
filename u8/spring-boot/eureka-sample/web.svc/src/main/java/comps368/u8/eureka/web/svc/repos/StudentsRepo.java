package comps368.u8.eureka.web.svc.repos;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import comps368.u8.eureka.web.svc.models.Students;

public interface StudentsRepo extends JpaRepository<Students, Integer>{
    @Query("select s from Students s where s.stdCode = ?1")
    Optional<Students> getByCode(String stdCode);
}
