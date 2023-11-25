package comps368.u5.crud.models;

import java.io.Serializable;
import java.util.Date;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Contacts implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cId;

    @Length(min = 2, max = 20, message = "Name must be between 2 and 20 characters")
    @Column(unique = true, nullable = false, length = 20)
    private String cName;

    @Column(nullable = false, length = 20)
    private String phone;

    @Email(message = "Email must be valid")
    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false, length = 1)
    private char gender;

    @Past(message = "Date of birth must be in the past")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date dob;
}