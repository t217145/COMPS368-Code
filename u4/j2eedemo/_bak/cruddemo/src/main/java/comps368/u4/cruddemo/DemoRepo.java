package comps368.u4.cruddemo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DemoRepo extends JpaRepository<DemoModel, Integer>{
	@Query("select s from DemoModel s where s.name like %:name")
	List<DemoRepo> findByName(String name);
}
