package com.mdoldur.usertask.service.impl.security;

import com.mdoldur.usertask.dto.LoginResultDTO;
import com.mdoldur.usertask.entity.UserEntity;
import com.mdoldur.usertask.repository.UserRepository;
import com.mdoldur.usertask.service.interfaces.LoginService;

import java.io.Console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	UserRepository userRepository;
	
    @Override
    public Boolean isLoggedIn(LoginResultDTO loginResultDTO) {
    	
        if ("admin".equals(loginResultDTO.getUserName()) && "admin".equals(loginResultDTO.getPassword())) {
        	System.out.println("loginResultDTO.getUserName() : " + loginResultDTO.getUserName());
        	
        	UserEntity user = userRepository.findByUname(loginResultDTO.getUserName());
        	if (user != null) {
        		return true;
        	}
            //return true;
        }
        return false;
    }

}
