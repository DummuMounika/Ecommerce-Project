package com.example.ecommerce.project.jwt.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

@Configuration
public class RedisConfig {
	
	@Value("${spring.redis.host}")
	private String hostName;
	
	@Value("${spring.redis.port}")
	private int port;
	
	  @Bean
	    public JedisConnectionFactory jedisConnectionFactory() {
	        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
	        redisStandaloneConfiguration.setHostName(hostName);
	        redisStandaloneConfiguration.setPort(port);
	        return new JedisConnectionFactory(redisStandaloneConfiguration);
	    }

	    @Bean
	    public RedisTemplate<String, Object> redisTemplate() {
	        RedisTemplate<String, Object> template = new RedisTemplate<>();
	        template.setConnectionFactory(jedisConnectionFactory());
	        template.setValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
	        return template;
	    }
	}