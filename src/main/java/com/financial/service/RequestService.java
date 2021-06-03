package com.financial.service;

import com.financial.entity.Request;

public interface RequestService {
	
	public Request getById(Long id);
	public Request save(Request request);

}
