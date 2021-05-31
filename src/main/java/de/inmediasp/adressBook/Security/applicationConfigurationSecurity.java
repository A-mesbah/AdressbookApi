package de.inmediasp.adressBook.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity//with this two @notations we will config every thing related with Security
public class applicationConfigurationSecurity extends WebSecurityConfigurerAdapter {
    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public applicationConfigurationSecurity(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()//simple to say here "what is your permissions in this System  ??"
                .antMatchers("/", "index")//what i want to show to users without any permission "this available for everyone without any Authentication "
                .permitAll()//to permit everyone to see this html page "index"--> just enter the root "localhost:8080 "
                .anyRequest()//you ask here "any request you get "
                .authenticated()//ask the owner of this request "Who are you ??"
                .and()//and
                .httpBasic();//i think this a type to encrypt my password to 64bit to make my password not readable to anyone hack the system
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
                .username("ahmed")
                .password(passwordEncoder.encode("123456"))
                .roles("contacts")
                .build();
        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    protected UserDetailsService passwordEncoder() {
        UserDetails user = User.builder()
                .username("ahmed")
                .password(passwordEncoder.encode("123456"))
                .roles("contacts")
                .build();
        return new InMemoryUserDetailsManager(user);
    }
}
