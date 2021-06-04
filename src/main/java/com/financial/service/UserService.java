package com.financial.service;

import org.springframework.security.core.context.SecurityContextHolder;

import com.financial.security.UserSecurityDetails;

public class UserService {
	
	public static UserSecurityDetails authenticated() {
		try {
			return (UserSecurityDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		catch (Exception e) {
			return null;
		}
	}
}
