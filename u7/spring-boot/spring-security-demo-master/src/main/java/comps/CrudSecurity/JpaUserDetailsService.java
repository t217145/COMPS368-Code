package comps.CrudSecurity;

import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository svc;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Optional<User> ou = svc.findUserByUname(name);
        ou.orElseThrow(() -> new UsernameNotFoundException("No user found with username " + name));
        User u = ou.get();

        ArrayList<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();

        return new org.springframework.security.core.userdetails.User(u.getUname(),u.getPassword(),auth);
    }

}