package comps368.u8.rest.basic.modes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Students {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int stdId;
    @Column(unique = true, nullable = false)
    private String stdCode;
    private String name;    
}
