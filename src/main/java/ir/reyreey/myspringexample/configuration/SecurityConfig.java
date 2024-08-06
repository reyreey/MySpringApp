package ir.reyreey.myspringexample.configuration;

import ir.reyreey.myspringexample.controller.customization.JwtAuthenticationFilter;
import ir.reyreey.myspringexample.repository.entities.Authority;
import ir.reyreey.myspringexample.repository.entities.Role;
import ir.reyreey.myspringexample.repository.entities.User;
import ir.reyreey.myspringexample.service.AuthorityService;
import ir.reyreey.myspringexample.service.DefaultUserService;
import ir.reyreey.myspringexample.service.RoleService;
import ir.reyreey.myspringexample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.ExceptionTranslationFilter;

import java.util.Set;

/**
 * @author : Reyreey
 * @mailto : dehghan.reyhaneh179@gmail.com
 * @created : 7/28/2024, Sunday
 **/
@Configuration
public class SecurityConfig {
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

//    @Bean
//    public CommandLineRunner commandLineRunner(UserService userService, AuthorityService authorityService, RoleService roleService) {
//        return args -> {
//            var select = authorityService.insertAuthority(new Authority("SELECT"));
//            var  insert = authorityService.insertAuthority(new Authority("INSERT"));
//            var change= authorityService.insertAuthority(new Authority("CHANGE"));
//            var remove= authorityService.insertAuthority(new Authority("REMOVE"));
//
//            var admin =roleService.insertRole(new Role("ADMIN", Set.of(select,insert,change,remove)));
//            var user =roleService.insertRole(new Role("USER", Set.of(select,insert)));
//
//            userService.insertUser(new User("admin","abcd1234",Set.of(admin,user)));
//            userService.insertUser(new User("reyreey","abcd1234",Set.of(user)));
//        };
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(DefaultUserService userService) {
        var provider=new DaoAuthenticationProvider();
        provider.setUserDetailsService(userService);
        provider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(provider);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
        security.csrf(AbstractHttpConfigurer::disable);//todo enable!!!!!!!!!!!!!!!!!!!

        security.addFilterAfter(jwtAuthenticationFilter, ExceptionTranslationFilter.class);

        security.authorizeHttpRequests(matchRegistry -> {
            matchRegistry.requestMatchers("api/auth").permitAll();
            matchRegistry.anyRequest().authenticated();
        });
        return security.build();
    }
}
