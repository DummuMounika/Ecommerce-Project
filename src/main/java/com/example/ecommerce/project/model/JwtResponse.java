package com.example.ecommerce.project.model;

import java.sql.Timestamp;

import com.example.ecommerce.project.utils.JsonTimestampSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class JwtResponse {
	
	private String jwtToken;
	private String username;
	@JsonSerialize(using = JsonTimestampSerializer.class)
	private Timestamp expiredTime;

}
