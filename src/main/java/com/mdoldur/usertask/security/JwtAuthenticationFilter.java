package com.mdoldur.usertask.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.mdoldur.usertask.service.impl.JwtUserDetailsServiceImpl;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	@Autowired
	TokenProvider tokenProvider;
	
	@Autowired
	JwtUserDetailsServiceImpl jwtUserDetails;
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String token = this.extractTokenFromRequest(request);
			if (token != null && StringUtils.hasText(token)) {
				Long userId = tokenProvider.getUserIdFromToken(token);
				UserDetails user = jwtUserDetails.loadUserById(userId);
				if (user != null) {
					UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
					auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					
					SecurityContextHolder.getContext().setAuthentication(auth);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		filterChain.doFilter(request, response);
	}
	
	private String extractTokenFromRequest(HttpServletRequest request) {
		String bearer = request.getHeader("Authorization");
		if (StringUtils.hasText(bearer) && bearer.startsWith("Bearer ")) {
			return bearer.substring("Bearer ".length() + 1);
		}
		return null;
	}

}
