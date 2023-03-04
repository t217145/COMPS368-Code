package comps368.u4.entitymanagerdemo;

import java.io.Serializable;
import org.hibernate.annotations.NamedQuery;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@NamedQuery(name = "DemoModel.getAll", query = "select d from DemoModel d")
@NamedQuery(name = "DemoModel.getByName", query = "select d from DemoModel d where d.name = :name")
@NamedQuery(name = "DemoModel.getById", query = "select d from DemoModel d where d.id = :id")
public class DemoModel implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String email;
}
