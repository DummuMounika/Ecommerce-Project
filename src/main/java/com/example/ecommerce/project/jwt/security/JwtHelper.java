package com.example.ecommerce.project.jwt.security;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Component
public class JwtHelper {
	
	 private SecretKey secretkey;
	 
	 //Required for token validity as of now 5 miliseconds
	 public static final long JWT_TOKEN_VALIDITY = 3 * 60 ;
	
	 @PostConstruct
	 public void generateNewScretKey() {
		 this.secretkey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
	 }
	 
	 //retrieve username from jwt token
	 public String getUsernameFromToken(String token) {
		 return getClaimFromToken(token,Claims::getSubject);
	 }
	 
	//retrieve expiration date from jwt token
	 public Timestamp getExpirationDateFromToken(String token) {
		 Date expirationDate = getClaimFromToken(token,Claims::getExpiration);
		 return new Timestamp(expirationDate.getTime());
	 }
	 
	 public <T> T getClaimFromToken(String token, Function<Claims,T> claimsResolver) {
		 final Claims claims = getAllClaimsFromToken(token);
		 return claimsResolver.apply(claims);
	 }
	 
	 //for retrieveing any information from token we will need the secret key
	 private Claims getAllClaimsFromToken(String token) {
		 return Jwts.parserBuilder()
				 .setSigningKey(secretkey)
				 .build().parseClaimsJws(token).getBody();
	 }
	 
	 //check if the token has expired
	 private Boolean isTokenExpired(String token) {
		 final Date expiration = getExpirationDateFromToken(token);
		 return expiration.before(new Date());
	 }
	 
	 public String generateToken(UserDetails userDetails) {
		 Map<String, Object> claims = new HashMap<>();
		 return doGenerateToken(claims, userDetails.getUsername());
	 }
	 
	 private String doGenerateToken(Map<String, Object> claims, String subject) {
		 
		 return Jwts.builder().setClaims(claims).setSubject(subject)
				 .setId(UUID.randomUUID().toString()) //added unique Id
				 .setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000)) 
				.signWith(secretkey)
				.compact();
	 }
	 
	 //validate token
	 public Boolean validateToken(String token, UserDetails userDetails) {
		 final String username = getUsernameFromToken(token);
		 return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	 }
	 
	 public String getTokenFromTokenId(String token) {
		 return getClaimFromToken(token, Claims::getId);
	 }
	
	
	
}

