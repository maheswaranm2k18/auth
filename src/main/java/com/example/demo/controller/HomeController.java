package com.example.demo.controller;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class HomeController {
	
	@GetMapping("/home")
	public String getHome() {
		return "Welcome to Springboot";
	}
	
	@GetMapping("/getSessionId")
	public String getSessionId(HttpServletRequest request) {
		return request.getSession().getId();
	}
	
	@GetMapping("/getCSRFtoken")
	public CsrfToken getCSRFtoken(HttpServletRequest request) {
		return (CsrfToken)request.getAttribute("_csrf");
	}

}
