package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.DemoUser;
import com.example.demo.repo.UserRepository;
import com.example.demo.service.JwtService;

@RestController
public class UserController {
	
//	@Autowired
//	private UserRepository repo;
//	
//	@PostMapping("/register")
//	public DemoUser register(@RequestBody DemoUser user) {
//		return repo.save(user);
//	}
	
	@Autowired
	private UserRepository repo;
	
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
	
	@PostMapping("/register")
	public DemoUser register(@RequestBody DemoUser user) {
		user.setPword(encoder.encode(user.getPword()));
		return repo.save(user);
	}
	
	@Autowired
	private JwtService jwtService;
	
//	@PostMapping("/login")
//	public String login(@RequestBody DemoUser user) {
////		System.out.println("User logged successfully"+user);
//		jwtService.verifyUser(user);
//		return "Success";
//	}
	
	@PostMapping("/login")
	public String login(@RequestBody DemoUser user) {
//		System.out.println("User logged successfully"+user);
		return jwtService.verifyUser(user);
	}
	
	

}
