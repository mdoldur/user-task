package com.mdoldur.usertask.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenProvider {
	private static final long serialVersionUID = -2550185165626007488L;
	private static String SECRET_KEY;
	private static long EXPIRES_IN;
	
	@Value("${app.secret.key}")
	private void setJwtSecretStatic(String secretKey) {
		TokenProvider.SECRET_KEY = secretKey;
	}
	
	@Value("${app.expires.in}")
	private void setJwtExpiresStatic(long expiresIn) {
		TokenProvider.EXPIRES_IN = expiresIn;
	}
	
	private static Claims getAllClaims(String token) {
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	}
	
	private static Date getExpirationDate(String token) {
		return getClaim(token, Claims::getExpiration);
	}

	private static <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaims(token);
		return claimsResolver.apply(claims);
	}
	
	private static String createToken(Map<String, Object> claims, String subject) {
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(subject)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRES_IN))
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY)
				.compact();
	}
	
	public static String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims, userDetails.getUsername());
	}
	
	public static String getUsername(String token) {
		return getClaim(token, Claims::getSubject);
	}

	public static Boolean isTokenExpired(String token) {
		return getExpirationDate(token).before(new Date());
	}
	
}
