package com.example.ecommerce.project.jwt.security;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * This class write the logic to check the token that is comming in header
 */

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    
	private static final Logger loggerMsg = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
	private JwtHelper jwtHelper;
	private UserDetailsService userDetailsService;
	
	@Autowired
	public JwtAuthenticationFilter(JwtHelper jwtHelper, UserDetailsService userDetailsService) {
		super();
		this.jwtHelper = jwtHelper;
		this.userDetailsService = userDetailsService;
	}
	
	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest request,@NonNull HttpServletResponse response,@NonNull FilterChain filterChain)
			throws ServletException, IOException {
		
		//Authorization
		String requestHeader = request.getHeader("Authorization");
		
		loggerMsg.info(" Header: {} ", requestHeader);
		String username = null;
		String token = null;
		
		if(requestHeader != null && requestHeader.startsWith("Bearer")) {
			//removing Bearer string from token
			token = requestHeader.substring(7);
			try {
				username = this.jwtHelper.getUsernameFromToken(token);
			
			}catch (IllegalArgumentException e) {
				loggerMsg.error("Illegal Argument while fetching the username!!");
				e.printStackTrace();
			}catch(ExpiredJwtException e) {
				loggerMsg.error("Given jwt token is expired !!");
                e.printStackTrace();		
			}catch(MalformedJwtException e) {
				 loggerMsg.error("Some changes has done in token !! Invalid Token");
	             e.printStackTrace();
			}catch(Exception e) {
				loggerMsg.error("Error occurred while processing the token", e);
	        }
			
		}else {
			loggerMsg.warn("Invalid Header Value !! ");
			
		}
		
		//
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			
			//fecth user deatil from username
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
			Boolean validateToken = this.jwtHelper.validateToken(token, userDetails);
		    if (Boolean.TRUE.equals(validateToken)) {
		    	//SET the authentication
		    	UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
		    	authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		        SecurityContextHolder.getContext().setAuthentication(authentication);
		   
		    }else {
		    	loggerMsg.info("Validation fails !!");
		    }
		
		}
		
		filterChain.doFilter(request, response);
		
		
	}

}
