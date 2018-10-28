package com.cs1699.project.blogspot.config;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.cs1699.project.blogspot.datasource.Blog;

@Component
public class ProducerService {

	@Autowired
	KafkaTemplate<String, Blog> template;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProducerService.class);
	
	public String publishEvent(String topic, Blog blog) {

		String result = "SUCCESS";
		
		if(template == null) {
			System.out.println("template is null");
			template = new ProducerConfiguration().kafkaTemplate();
		}
		
		try {
				template.send(topic, blog).get(10, TimeUnit.SECONDS);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			result = "FAILURE";
		}
		System.out.println("result == " + result);
		return result; 
	}	
}
