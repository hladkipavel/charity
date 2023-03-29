package pl.coderslab.charity.user;

import lombok.AllArgsConstructor;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SpringDataUserDetailsService implements UserDetailsService {
    private final UserServiceImpl userServiceImpl;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userServiceImpl.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(email);
        }
        if(user.isBlocked()){
            throw new LockedException("User account is locked");
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        user.getRoles().forEach(r ->
                grantedAuthorities.add(new SimpleGrantedAuthority(r.getName())));
        return new CustomUser(user.getEmail(), user.getPassword(), user.isEnabled(), true, true,true, grantedAuthorities, user.getFirstName());
    }

    public class CustomUser extends org.springframework.security.core.userdetails.User {
        private String name;

        public CustomUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, String name) {
            super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
            this.name = name;
        }
        public String getName() {
            return name;
        }
    }
}

