package sk.stuba.fei.nemocnica.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import sk.stuba.fei.nemocnica.domain.Zamestnanec;
import sk.stuba.fei.nemocnica.service.ZamestnanecService;

public class CustomUserDetailService implements UserDetailsService {

    private ZamestnanecService zamestnanecService;


    public CustomUserDetailService(ZamestnanecService zamestnanecService) {
        this.zamestnanecService = zamestnanecService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (zamestnanecService == null) {
            throw new UsernameNotFoundException("Failed to autowire zamestnanecService.");
        }
        Zamestnanec z = zamestnanecService.findByUsername(username);
        if (z == null) {
            throw new UsernameNotFoundException("User with username " + username + " not found.");
        }
        if (z.getAuthorities() == null || z.getAuthorities().isEmpty()) {
            throw new UsernameNotFoundException("User has no granted authorities.");
        }
        return new User(
                z.getUsername(),
                z.getPassword(),
                z.isEnabled(),
                z.isAccountNonExpired(),
                z.isCredentialsNonExpired(),
                z.isAccountNonLocked(),
                z.getAuthorities()
        );
    }
}
