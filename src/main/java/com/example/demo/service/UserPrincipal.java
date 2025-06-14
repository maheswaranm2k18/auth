package com.example.demo.service;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.entity.DemoUser;

public class UserPrincipal implements UserDetails {
	
	private DemoUser user;
	
	public UserPrincipal(DemoUser user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
//		return null;
		return Collections.singleton(new SimpleGrantedAuthority("USER")); //singleton return only one object
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
//		return null;
		return user.getPword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getName();
	}

}
