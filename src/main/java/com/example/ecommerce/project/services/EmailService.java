package com.example.ecommerce.project.services;

import java.util.concurrent.CompletableFuture;

import com.example.ecommerce.project.model.EmailDetails;

public interface EmailService {
	
	public CompletableFuture<String> sendEmail(EmailDetails details);
	
}
