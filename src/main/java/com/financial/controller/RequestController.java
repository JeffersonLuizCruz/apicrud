package com.financial.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.financial.entity.Request;
import com.financial.event.Event;
import com.financial.service.RequestServiceImpl;

@RestController
@RequestMapping("requests")
public class RequestController {
	
	@Autowired private RequestServiceImpl requestService;
	@Autowired private ApplicationEventPublisher publisher;
	
	
	
	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Request> getById(@PathVariable Long id){
		Request result = requestService.getById(id);
		return ResponseEntity.ok(result);
	}
	
	@PostMapping
	public ResponseEntity<Request> saveCategory(@Valid @RequestBody Request request, HttpServletResponse response){
		Request createRequest = requestService.save(request);
		publisher.publishEvent(new Event(this, response, createRequest.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(createRequest);
	}

}
