package com.mdoldur.usertask.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class TokenProvider {

	private static final long serialVersionUID = -2550185165626007488L;

	private static String SECRET_KEY;

	@Value("${app.secret.key}")
	private String secretKey;

	public static final long JWT_TTL = 60 * 60 * 10;

	public static String getUsername(String token) {
		return getClaim(token, Claims::getSubject);
	}

	public static Date getExpirationDate(String token) {
		return getClaim(token, Claims::getExpiration);
	}

	public static <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private static Claims getAllClaims(String token) {
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	}

	private static Boolean isTokenExpired(String token) {
		return getExpirationDate(token).before(new Date());
	}

	public static String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims, userDetails.getUsername());
	}

	private static String createToken(Map<String, Object> claims, String subject) {
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(subject)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * JWT_TTL))
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY)
				.compact();
	}

	@Value("${app.secret.key}")
	public void setJwtSecretStatic(String secretKey){
		TokenProvider.SECRET_KEY = secretKey;
	}

	public static Boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

}
