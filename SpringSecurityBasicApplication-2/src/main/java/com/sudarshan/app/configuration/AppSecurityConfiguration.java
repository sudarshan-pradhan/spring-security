package com.sudarshan.app.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * 
 * @author sudarshanpradhan
 *
 */
@Configuration
public class AppSecurityConfiguration {

	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests( (auth)->auth
				.antMatchers("/myAccount","/myBalance","/myLoans","/myCards").authenticated()
				.antMatchers("/notices","/contact").permitAll()
		).httpBasic(Customizer.withDefaults());
		return http.build();

	}
	
	/**
	 * Approach-1
	 * 
	 * method for in-memory users for spring security
	 * users and their roles have to be defined in the same method
	 * 
	 * @return
	 */
	@Bean
    public InMemoryUserDetailsManager userDetailsService() {
    	UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("12345")
                .authorities("admin")
                .build();
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("12345")
                .authorities("read")
                .build();
        return new InMemoryUserDetailsManager(admin, user);
    }
}
