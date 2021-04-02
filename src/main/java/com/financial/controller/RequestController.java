package com.financial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.financial.entity.Request;
import com.financial.service.RequestServiceImpl;

@RestController
@RequestMapping("requests")
public class RequestController {
	
	@Autowired private RequestServiceImpl requestService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Request> getById(@PathVariable Long id){
		Request result = requestService.getById(id);
		return ResponseEntity.ok(result);
	}

}
