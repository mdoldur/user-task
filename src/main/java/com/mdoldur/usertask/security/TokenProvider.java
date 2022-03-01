package com.mdoldur.usertask.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
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
	@Value("${app.secret.key}")
	private String SECRET_KEY;
	
	@Value("${app.expires.in}")
	private long EXPIRES_IN;
	
	public String generateToken(Authentication auth) {
		JwtUserDetails user = (JwtUserDetails)auth.getPrincipal();
		Date expireDate = new Date(new Date().getTime() + EXPIRES_IN); 
		
		return Jwts.builder()
					.setSubject(String.valueOf(user.getId()))
					.setIssuedAt(new Date())
					.setExpiration(expireDate)
					.signWith(SignatureAlgorithm.HS512, SECRET_KEY)
					.compact();
	}
	
	public Long getUserIdFromToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}
	
	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
			return !this.isTokenExpired(token);
		} catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private boolean isTokenExpired(String token) {
		Date expireDate = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getExpiration();
		return expireDate.before(new Date());		
	}

}
