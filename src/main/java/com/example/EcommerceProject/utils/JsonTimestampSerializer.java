package com.example.EcommerceProject.utils;


import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

@Component
public class JsonTimestampSerializer extends JsonSerializer<Timestamp>{

@Override
public void serialize(Timestamp timestamp, JsonGenerator generator,
		SerializerProvider provider) throws IOException, JsonProcessingException {
	
	SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss a z");
	generator.writeString(formatter.format(timestamp));
}
 
}