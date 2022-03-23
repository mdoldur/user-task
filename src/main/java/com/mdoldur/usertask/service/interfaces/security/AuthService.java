package com.mdoldur.usertask.service.interfaces.security;

import com.mdoldur.usertask.requests.UserRequest;
import com.mdoldur.usertask.responses.AuthResponse;

public interface AuthService {

	public AuthResponse login(UserRequest loginRequest);
	
}
