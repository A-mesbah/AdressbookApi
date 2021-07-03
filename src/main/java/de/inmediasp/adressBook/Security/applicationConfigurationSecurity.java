package de.inmediasp.adressBook.Security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;



@Configuration
@EnableWebSecurity
public class applicationConfigurationSecurity extends WebSecurityConfigurerAdapter {
    private final PasswordEncoder passwordEncoder;



    @Autowired
    public applicationConfigurationSecurity(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()//Authorize any
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
        UserDetails ahmed  = User.builder()
                .username("ahmed")
                .password(passwordEncoder.encode("123"))
                .roles("USER")
                .build();

        UserDetails thomas  = User.builder()
                .username("thomas")
                .password(passwordEncoder.encode("123456"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(ahmed,thomas);
    }
}


