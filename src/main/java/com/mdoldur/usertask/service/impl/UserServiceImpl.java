package com.mdoldur.usertask.service.impl;

import com.mdoldur.usertask.entity.GnlStEntity;
import com.mdoldur.usertask.entity.UserEntity;
import com.mdoldur.usertask.enums.StatusEnum;
import com.mdoldur.usertask.repository.UserRepository;
import com.mdoldur.usertask.requests.UserRequest;
import com.mdoldur.usertask.service.interfaces.UserService;
import com.mdoldur.usertask.service.interfaces.common.GnlStService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    GnlStService gnlStService;
    
    public UserEntity findUserByUname(String userName) {
    	return userRepository.findByUname(userName);
    }
    
    private ResponseEntity<String> createUser(UserRequest registerRequest, GnlStEntity status) {
		UserEntity user = new UserEntity();
		user.setUname(registerRequest.getUserName());
		user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
		user.setName(registerRequest.getUserName());
		user.setSurname(registerRequest.getUserName());
		user.setStId(status.getGnlStId());
		userRepository.save(user);
		return new ResponseEntity<>("User created", HttpStatus.CREATED);
    }
    
	public ResponseEntity<String> register(UserRequest registerRequest) {
		if (this.findUserByUname(registerRequest.getUserName()) != null) {
			return new ResponseEntity<>("Username already in use", HttpStatus.BAD_REQUEST);
		}
		
		GnlStEntity status = gnlStService.findByEntCodeNameAndShrtCode("USER", StatusEnum.ACTV.toString());
		if (status != null) {
			return this.createUser(registerRequest, status);
		}
		
		return new ResponseEntity<>("User can not created", HttpStatus.BAD_REQUEST);
	}
    
}
