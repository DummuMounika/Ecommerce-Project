package com.example.ecommerce.project.controllers;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.project.model.EmailDetails;
import com.example.ecommerce.project.services.EmailService;


@RestController
public class EmailController {
	
	@Autowired
	private EmailService emailService;
	
	@PostMapping("/sendMail")
	public String sendMail(@RequestBody EmailDetails details) throws InterruptedException, ExecutionException {
		CompletableFuture<String> status = emailService.sendEmail(details);
		return status.get();
		
	}
	

}
