package comps.CrudSecurity;

import java.util.Optional;
import javax.annotation.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

@Resource
public interface UserRepository extends JpaRepository<User, Integer>{
    public Optional<User> findUserByUname(String uname);
}