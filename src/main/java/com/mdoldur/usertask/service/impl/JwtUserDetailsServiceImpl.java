package com.mdoldur.usertask.service.impl;

import com.mdoldur.usertask.dto.TokenizedUser;
import com.mdoldur.usertask.entity.UserEntity;
import com.mdoldur.usertask.repository.UserRepository;
import com.mdoldur.usertask.security.JwtUserDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

	private static List<GrantedAuthority> mapToGrantedAuthorities(UserEntity user) {
		return null;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity user = userRepository.findByUname(email);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with email: " + email);
		}

		return createTokenizedUser(user);
	}

	private TokenizedUser createTokenizedUser(UserEntity user) {
		return new TokenizedUser(
				user.getUserId(),
				user.getUname(),
				user.getPassword(),
				mapToGrantedAuthorities(user)
		);
	}

}
