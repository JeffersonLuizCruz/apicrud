package com.financial.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.financial.security.JWTUtil;
import com.financial.security.UserSecurityDetails;
import com.financial.service.UserService;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {
	
	@Autowired private JWTUtil jwtUtil;
	
	//@Autowired private AuthService service;
	
	@PostMapping(value = "/refresh_token")
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
		UserSecurityDetails user = UserService.authenticated();
		String token = jwtUtil.generateToken(user.getUsername());
		response.addHeader("Authorization", "Bearer " + token);
		response.addHeader("access-control-expose-headers", "Authorization");
		return ResponseEntity.noContent().build();
	}
	
//	@PostMapping(value = "/forgot")
//	public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDTO objDto) {
//		service.sendNewPassword(objDto.getEmail());
//		return ResponseEntity.noContent().build();
//	}

}
