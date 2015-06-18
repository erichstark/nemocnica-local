package sk.stuba.fei.team.local.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import sk.stuba.fei.team.local.domain.Employee;
import sk.stuba.fei.team.local.repository.EmployeeRepository;

public class CustomUserDetailService implements UserDetailsService {

    private EmployeeRepository employeeRepository;


    public CustomUserDetailService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (employeeRepository == null) {
            throw new UsernameNotFoundException("Failed to autowire employeeRepository.");
        }
        Employee z = employeeRepository.findOne(username);
        if (z == null) {
            throw new UsernameNotFoundException("User with username " + username + " not found.");
        }
        if (z.getAuthorities() == null || z.getAuthorities().isEmpty()) {
            throw new UsernameNotFoundException("User has no granted authorities.");
        }
        return new CustomUser(
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
