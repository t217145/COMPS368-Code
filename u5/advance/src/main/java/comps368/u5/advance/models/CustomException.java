package comps368.u5.advance.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false) // due to extended Exception
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomException extends Exception {
    private String errCode;
    private String errMsg;
    private String rtnUrl;
}
