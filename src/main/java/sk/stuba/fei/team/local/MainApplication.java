package sk.stuba.fei.team.local;

import org.hornetq.api.core.TransportConfiguration;
import org.hornetq.core.remoting.impl.netty.NettyAcceptorFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.hornetq.HornetQConfigurationCustomizer;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import sk.stuba.fei.team.local.domain.Employee;
import sk.stuba.fei.team.local.repository.EmployeeRepository;
import sk.stuba.fei.team.local.security.CustomUserDetailService;
import sk.stuba.fei.team.local.security.PBKDF2WithHmacSHA1;

import javax.jms.ConnectionFactory;
import javax.sql.DataSource;
import java.util.*;

@SpringBootApplication
@EnableJms
@EnableScheduling
public class MainApplication extends WebMvcConfigurerAdapter {

    final static Logger logger = LoggerFactory.getLogger(MainApplication.class);

    @Value("${server.address:localhost}")
    String serverAddress;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    CustomInterceptor customInterceptor;

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(MainApplication.class, args);
        EmployeeRepository employeeRepository = context.getBean(EmployeeRepository.class);
        PasswordEncoder encoder = new PBKDF2WithHmacSHA1();
        if (employeeRepository.findOne("admin") == null) {
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ADMIN"));
            Employee userDetails = new Employee("admin", encoder.encode("admin123"), authorities);
            employeeRepository.save(userDetails);
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

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
        registry.addInterceptor(customInterceptor);
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
            params.put("host", serverAddress);
            params.put("port", "5445");
            logger.info("Starting new JMS acceptor on " + serverAddress + ":5445");
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
        private EmployeeRepository employeeRepository;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable()
                    .authorizeRequests()
                    .antMatchers("/admin/**", "/manage", "/setup/**").hasAuthority("ADMIN")
                    .antMatchers("/css/**", "/js/**", "/fonts/**", "/img/**", "/fav/**", "/service/**").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .formLogin().loginPage("/login").failureUrl("/login?error").permitAll()
                    .and()
                    .logout().permitAll();
        }

        @Override
        public void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(new CustomUserDetailService(employeeRepository)).passwordEncoder(new PBKDF2WithHmacSHA1());
            auth.jdbcAuthentication().dataSource(dataSource);
        }
    }


}
