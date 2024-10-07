package com.example.ecommerce.project.services;

import java.io.File;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.ecommerce.project.model.EmailDetails;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl  implements EmailService{
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Value("${spring.mail.username}")
	private String sender;

	@Override
	@Async
	public CompletableFuture<String> sendEmail(EmailDetails details) {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper;
		try {
			mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			mimeMessageHelper.setFrom(sender);
			mimeMessageHelper.setTo(details.getRecipient());
			mimeMessageHelper.setText(details.getMsgBody(), details.getIsHtmlContent());
			mimeMessageHelper.setSubject(details.getSubject());
			if(details.getAttachment() != null) {
		    	mimeMessageHelper.addAttachment(details.getAttachment().getName(), details.getAttachment());
		    }
			javaMailSender.send(mimeMessage);
			return CompletableFuture.completedFuture("Mail sent Successfully");

		}catch(MessagingException e) {
			return CompletableFuture.completedFuture("Error while sending a email..");
		}
	}
	
//	//To send a email with attachment
//	public String sendMailWithAttachment(EmailDetails details) {
//		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//		MimeMessageHelper mimeMessageHelper;
//		
//		try {
//			mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
//			mimeMessageHelper.setFrom(sender);
//			mimeMessageHelper.setTo(details.getRecipient());
//			mimeMessageHelper.setText(details.getMsgBody());
//	        mimeMessageHelper.setSubject(details.getSubject());
//	        
//	     //   FileSystemResource file = new FileSystemResource(new File(details.getAttachment()));
//	        if (!file.exists()) {
//	            return "File not found: " + details.getAttachment();
//	        }
//	        if (!file.isReadable()) {
//	            return "File is not readable: " + details.getAttachment();
//	        }  
//	        mimeMessageHelper.addAttachment(file.getFilename(), file);
//	        
//	        javaMailSender.send(mimeMessage);
//	        return "Mail sent Successfully";
//	        
//		}catch (MessagingException e) {
//			return "Error while sending attachment mail!!! ";
//		}
	//}
	

}
