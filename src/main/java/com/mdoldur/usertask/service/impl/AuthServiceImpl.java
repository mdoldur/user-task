package com.mdoldur.usertask.service.impl;

import com.mdoldur.usertask.requests.UserRequest;
import com.mdoldur.usertask.responses.AuthResponse;
import com.mdoldur.usertask.security.TokenProvider;
import com.mdoldur.usertask.service.interfaces.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;

    private Authentication doUsernamePasswordAuthentication(UserRequest loginRequest) {
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword());
        Authentication auth = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(auth);
        return auth;
    }

    public AuthResponse login(UserRequest loginRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword())
            );

            final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUserName());
            final String jwt = TokenProvider.generateToken(userDetails);

            AuthResponse response = new AuthResponse();
            response.setMessage(jwt);

            return response;
        } catch (BadCredentialsException | UsernameNotFoundException e) {
            AuthResponse response = new AuthResponse();
            response.setMessage("Email or Password invalid");

            return response;
        }
    }

}
