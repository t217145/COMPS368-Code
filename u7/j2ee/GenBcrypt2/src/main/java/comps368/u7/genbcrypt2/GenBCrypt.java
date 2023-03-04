package comps368.u7.genbcrypt2;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GenBCrypt {
      public static void main(String[] args) {

	int i = 0;
	while (i < 10) {
            String password = "bobpass";
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(password);

            System.out.println(hashedPassword);
            i++;
	}

  }
}
