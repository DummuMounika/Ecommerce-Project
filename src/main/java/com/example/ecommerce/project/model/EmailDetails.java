package com.example.ecommerce.project.model;

import java.io.File;

public class EmailDetails {
	
	private String recipient;
	private String msgBody;
	private String subject;
	private File attachment;
	private boolean isHtmlContent;
	
	public String getRecipient() {
		return recipient;
	}
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	public String getMsgBody() {
		return msgBody;
	}
	public void setMsgBody(String msgBody) {
		this.msgBody = msgBody;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public File  getAttachment() { // type - File
		return attachment;
	}
	public void setAttachment(File  attachment) {
		this.attachment = attachment;
	}
	public Boolean getIsHtmlContent() {
		return isHtmlContent;
	}
	public void setIsHtmlContent(Boolean isHtmlContent) {
		this.isHtmlContent = isHtmlContent;
	}
	
	public EmailDetails(String recipient, String msgBody, String subject, File attachment, boolean isHtmlContent) {
		super();
		this.recipient = recipient;
		this.msgBody = msgBody;
		this.subject = subject;
		this.attachment = attachment;
		this.isHtmlContent = isHtmlContent;
	}
	public EmailDetails() {
		super();
	}
	
	@Override
	public String toString() {
		return "EmailDetails [recipient=" + recipient + ", msgBody=" + msgBody + ", subject=" + subject
				+ ", attachment=" + attachment + "]";
	}
	
	
	
	

}
