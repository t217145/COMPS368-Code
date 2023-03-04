package comps368.u8demo;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StdRepository extends CrudRepository<Students, Integer>{

    @Query("select s from Students s where ouhkID = ?1")
    public List<Students> findAllByOuhkId(String ouhkId);

}