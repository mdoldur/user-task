package com.mdoldur.usertask.service.impl.security;

import com.mdoldur.usertask.requests.UserRequest;
import com.mdoldur.usertask.responses.AuthResponse;
import com.mdoldur.usertask.security.TokenProvider;
import com.mdoldur.usertask.security.TokenizedUser;
import com.mdoldur.usertask.service.interfaces.security.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    //@Autowired
    //private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private ApplicationContext appContext;
    
    public AuthResponse login(UserRequest loginRequest) {
        try {
        	AuthenticationManager authenticationManager = appContext.getBean(AuthenticationManager.class);
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));
            
            final TokenizedUser tokenizedUser = (TokenizedUser) userDetailsService.loadUserByUsername(loginRequest.getUserName());
            final String jwt = TokenProvider.generateToken(tokenizedUser);

            AuthResponse response = new AuthResponse();
            response.setUserId(tokenizedUser.getId());
            response.setMessage(jwt);

            return response;
        } catch (BadCredentialsException | UsernameNotFoundException e) {
            AuthResponse response = new AuthResponse();
            response.setMessage("Username or Password invalid");

            return response;
        }
    }

}
