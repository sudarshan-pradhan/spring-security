package com.sudarshan.app.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
//	
//	/**
//	 * Approach-1
//	 * 
//	 * method for in-memory users for spring security
//	 * users and their roles have to be defined in the same method
//	 * 
//	 * @return
//	 */
//	@Bean
//    public InMemoryUserDetailsManager userDetailsService() {
//    	UserDetails admin = User.withDefaultPasswordEncoder()
//                .username("admin")
//                .password("12345")
//                .authorities("admin")
//                .build();
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("user")
//                .password("12345")
//                .authorities("read")
//                .build();
//        return new InMemoryUserDetailsManager(admin, user);
//    }
	
	/**
	 * Approach-2
	 * 
	 * where we don't define password encoder
	 * while creating the user details. Instead a separate
	 * PasswordEncoder bean will be created.
	 * 
	 * @return
	 */
	@Bean
	public InMemoryUserDetailsManager userDetailsService() {
        InMemoryUserDetailsManager userDetailsService = new InMemoryUserDetailsManager();
        UserDetails admin = User.withUsername("admin").password("12345").authorities("admin").build();
        UserDetails user = User.withUsername("user").password("12345").authorities("read").build();
        userDetailsService.createUser(admin);
        userDetailsService.createUser(user);
        return userDetailsService;
	}
	
    /**
     * Approach-2
     * 
     * NoOpPasswordEncoder is not recommended for production usage.
     * Use only for non-prod.
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
	
}
