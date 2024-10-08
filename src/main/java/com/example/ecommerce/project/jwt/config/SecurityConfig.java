
package com.example.ecommerce.project.jwt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.ecommerce.project.jwt.security.JwtAuthenticationEntryPoint;
import com.example.ecommerce.project.jwt.security.JwtAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
	
	private JwtAuthenticationEntryPoint point;
	private JwtAuthenticationFilter filter;
	
    private UserDetailsService userDetailsService;
    private PasswordEncoder passwordEncoder;
    
    private static final String[] SWAGGER_WHITELIST = {
    		"/swagger-ui/**", // API's public face
    		"/v3/api-docs/**", //where all the API details live
    		"/swagger-resources/**",
    		"/swagger-resources",
    		"/swagger-ui.html"
    		
    };
	
	@Autowired
	public SecurityConfig(JwtAuthenticationEntryPoint point, JwtAuthenticationFilter filter,
			UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
		super();
		this.point = point;
		this.filter = filter;
		this.userDetailsService = userDetailsService;
		this.passwordEncoder = passwordEncoder;
	}
	


	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		//configuration
		
		http.csrf(csrf->csrf.disable()).
				cors(cors->cors.disable())
				.authorizeHttpRequests(
						auth-> 
						auth.requestMatchers("/public/").authenticated().
						requestMatchers("/actuator/**").permitAll().
						requestMatchers("/login").permitAll().
						requestMatchers(SWAGGER_WHITELIST).permitAll().
						requestMatchers("/customers").permitAll().
						//requestMatchers("/public/customers/**").permitAll().
						requestMatchers("/h2-console/**").permitAll()
						.anyRequest().authenticated())
				.exceptionHandling(ex->ex.authenticationEntryPoint(point))
				.sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
	    
		return http.build();
	}
	

	@Bean
	public DaoAuthenticationProvider doDaoaAuthenicationProvider() {
		DaoAuthenticationProvider doAuthenticationProvider = new DaoAuthenticationProvider();
		doAuthenticationProvider.setUserDetailsService(userDetailsService);
		doAuthenticationProvider.setPasswordEncoder(passwordEncoder);
		return doAuthenticationProvider;
		
	}

}
