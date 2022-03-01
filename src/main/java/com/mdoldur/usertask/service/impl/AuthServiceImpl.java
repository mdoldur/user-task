package com.mdoldur.usertask.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.mdoldur.usertask.entity.UserEntity;
import com.mdoldur.usertask.repository.UserRepository;
import com.mdoldur.usertask.requests.UserRequest;
import com.mdoldur.usertask.responses.AuthResponse;
import com.mdoldur.usertask.security.TokenProvider;
import com.mdoldur.usertask.service.interfaces.AuthService;

@Service
public class AuthServiceImpl implements AuthService {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private TokenProvider tokenProvider;
	@Autowired
	private UserRepository userRepository;
	
	private Authentication doUsernamePasswordAuthentication(UserRequest loginRequest) {
		UsernamePasswordAuthenticationToken authToken =
				new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword());
		Authentication auth = authenticationManager.authenticate(authToken);
		SecurityContextHolder.getContext().setAuthentication(auth);
		return auth;
	}
	
	private AuthResponse response(UserEntity user, String token) {
		AuthResponse response = new AuthResponse();
		response.setUserId(user.getUserId());
		response.setMessage(token);
		return response;
	}
	
	public AuthResponse login(UserRequest loginRequest) {
		Authentication auth = this.doUsernamePasswordAuthentication(loginRequest);
		
		UserEntity user = userRepository.findByUname(loginRequest.getUserName());
		if (user != null) {
			String token = tokenProvider.generateToken(auth);
			return this.response(user, token);
		}
		return null;
	}
	
}
