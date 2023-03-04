package comps368.u8.eureka.web.mvc.models;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Students implements Serializable {
    private int stdId;
    private String stdCode;
    private String stdName; 
    private char gender;
}