package com.financial.repository.reposervice;

import com.financial.entity.Request;

public interface RequestService {
	
	public Request getById(Long id);
	public Request save(Request request);

}
