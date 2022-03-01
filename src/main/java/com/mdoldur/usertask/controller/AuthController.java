package com.mdoldur.usertask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mdoldur.usertask.requests.UserRequest;
import com.mdoldur.usertask.responses.AuthResponse;
import com.mdoldur.usertask.service.interfaces.AuthService;
import com.mdoldur.usertask.service.interfaces.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	private AuthService authService;
	@Autowired
	private UserService userService;
	
	@PostMapping("/loginx")
	public AuthResponse loginx(@RequestBody UserRequest loginRequest) {
		return authService.login(loginRequest);
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody UserRequest registerRequest) {
		return userService.register(registerRequest);
	}
	
}
