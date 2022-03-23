package com.mdoldur.usertask.service.impl.security;

import com.mdoldur.usertask.entity.UserEntity;
import com.mdoldur.usertask.repository.UserRepository;
import com.mdoldur.usertask.security.TokenizedUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		UserEntity user = userRepository.findByUname(userName);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with userName: " + userName);
		}

		return TokenizedUser.create(user);
	}
	
}