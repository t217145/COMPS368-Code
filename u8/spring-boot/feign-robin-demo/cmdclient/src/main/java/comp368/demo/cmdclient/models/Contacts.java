package comp368.demo.cmdclient.models;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Contacts implements Serializable{

    private int id;

    private String name;

    private String phone;

    private String email;
}