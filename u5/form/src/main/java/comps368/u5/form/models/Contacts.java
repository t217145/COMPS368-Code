package comps368.u5.form.models;

import java.io.Serializable;
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
    private String cName;
    private String phone;
    private String email;
    private boolean isMale;
}