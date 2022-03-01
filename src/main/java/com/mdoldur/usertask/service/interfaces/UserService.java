package com.mdoldur.usertask.service.interfaces;

import org.springframework.http.ResponseEntity;

import com.mdoldur.usertask.entity.UserEntity;
import com.mdoldur.usertask.requests.UserRequest;

public interface UserService {
	
    public UserEntity findUserByUname(String userName);
    
	public ResponseEntity<String> register(UserRequest registerRequest);

}
