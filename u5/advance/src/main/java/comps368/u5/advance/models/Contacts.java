package comps368.u5.advance.models;

import java.io.Serializable;
import java.util.Date;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
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
public class Contacts implements Serializable {
    private int cId;

    @Length(min = 2, max = 20, message = "Name must be between 2 and 20 characters")
    private String cName;

    private String phone;

    @Email(message = "Email must be valid")
    private String email;

    private char gender;

    @Past(message = "Date of birth must be in the past")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dob;
}