package de.cogamemonolith.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Represents a class for authentication and authorisation management of all modules in the project
 * Overrides default de.cogamemonolith.security configurations of spring-boot-starter-de.cogamemonolith.security
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    /**
     * Defines which URL-paths should be secured
     *
     */
    //TODO: remove after implementing authentication
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        //allow access for all paths without any authenication
        http.csrf().disable().authorizeRequests().anyRequest().permitAll();
    }

    /**
     * @return password encoder bean, which provides encryption and decryption of user's password
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
