package com.sudarshan.app.configuration;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
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
	 * This appraoach leverages a sql Database to manage application authentication and authorization
	 * To check find the sql script in the resources folder.
	 * 
	 * @param dataSource
	 * @return
	 */
	@Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
	  return new JdbcUserDetailsManager(dataSource);
    }
	
    /**
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
