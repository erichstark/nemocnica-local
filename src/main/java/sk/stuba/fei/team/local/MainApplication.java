package sk.stuba.fei.team.local;

import org.hornetq.api.core.TransportConfiguration;
import org.hornetq.core.remoting.impl.netty.NettyAcceptorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.hornetq.HornetQConfigurationCustomizer;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import sk.stuba.fei.team.local.domain.Employee;
import sk.stuba.fei.team.local.security.CustomUser;
import sk.stuba.fei.team.local.security.CustomUserDetailService;
import sk.stuba.fei.team.local.security.PBKDF2WithHmacSHA1;
import sk.stuba.fei.team.local.service.EmployeeService;

import javax.jms.ConnectionFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.util.*;

@SpringBootApplication
@EnableJms
public class MainApplication extends WebMvcConfigurerAdapter {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(MainApplication.class, args);
        initializeUsers(context);
    }

    private static void initializeUsers(ConfigurableApplicationContext context) {
        EmployeeService employeeService = context.getBean(EmployeeService.class);
        PasswordEncoder encoder = new PBKDF2WithHmacSHA1();
        if (employeeService.findByUsername("user") == null) {
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("USER"));
            Employee userDetails = new Employee("user", encoder.encode("user123"), authorities);
            employeeService.save(userDetails);
        }
        if (employeeService.findByUsername("admin") == null) {
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ADMIN"));
            Employee userDetails = new Employee("admin", encoder.encode("admin123"), authorities);
            employeeService.save(userDetails);
        }
    }

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(new Locale("sk"));
        return slr;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    @Bean
    HandlerInterceptorAdapter userDetailsInterceptor() {
        return new UserDetailsInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
        registry.addInterceptor(userDetailsInterceptor());
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }

    @Bean
    public ApplicationSecurity applicationSecurity() {
        return new ApplicationSecurity();
    }

    @Bean
    public HornetQConfigurationCustomizer hornetCustomizer() {
        return configuration -> {
            Set<TransportConfiguration> acceptors = configuration.getAcceptorConfigurations();
            Map<String, Object> params = new HashMap<>();
            params.put("host", "localhost");
            params.put("port", "5445");
            TransportConfiguration tc = new TransportConfiguration(NettyAcceptorFactory.class.getName(), params);
            acceptors.add(tc);
        };
    }

    @Bean
    JmsTemplate jmsTopicTemplate(ConnectionFactory connectionFactory) {
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
        jmsTemplate.setPubSubDomain(true);
        jmsTemplate.setDefaultDestinationName("displayCallIn");
        return jmsTemplate;
    }

    @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
    protected static class ApplicationSecurity extends WebSecurityConfigurerAdapter {

        @SuppressWarnings("SpringJavaAutowiringInspection")
        @Autowired
        private DataSource dataSource;

        @SuppressWarnings("SpringJavaAutowiringInspection")
        @Autowired
        private EmployeeService employeeService;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable()
                    .authorizeRequests()
                    .antMatchers("/admin/**", "/manage").hasAuthority("ADMIN")
                    .antMatchers("/css/**", "/js/**", "/fonts/**", "/img/**", "/fav/**", "/service/**").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .formLogin().loginPage("/login").failureUrl("/login?error").permitAll()
                    .and()
                    .logout().permitAll();
        }

        @Override
        public void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(new CustomUserDetailService(employeeService)).passwordEncoder(new PBKDF2WithHmacSHA1());
            auth.jdbcAuthentication().dataSource(dataSource);
        }
    }

    public class UserDetailsInterceptor extends HandlerInterceptorAdapter {

        @Override
        public void postHandle(final HttpServletRequest request,
                               final HttpServletResponse response, final Object handler,
                               final ModelAndView modelAndView) throws Exception {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                CustomUser userDetails = (CustomUser) principal;
                if (modelAndView != null) {
                    modelAndView.getModelMap().
                            addAttribute("user", userDetails);
                }
            }
        }
    }
}
