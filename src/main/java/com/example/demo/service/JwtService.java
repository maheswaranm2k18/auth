package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.demo.entity.DemoUser;

@Service
public class JwtService {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private JWTTokenService jwtTokenService;

	public String verifyUser(DemoUser user) {
		// TODO Auto-generated method stub
		Authentication authentication = 
				authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getName(), user.getPword()));
		if(authentication.isAuthenticated()) {
			return jwtTokenService.generateToken(user.getName());
		} else {
			return "Fail";
		}
	}
	
	

}
