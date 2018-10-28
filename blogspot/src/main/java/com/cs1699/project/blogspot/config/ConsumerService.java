package com.cs1699.project.blogspot.config;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.cs1699.project.blogspot.datasource.Blog;

@Component
public class ConsumerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProducerService.class);
	
	private CountDownLatch latch = new CountDownLatch(1);
	
	private ArrayList<Blog> blogs = new ArrayList<>();
	
	public CountDownLatch getLatch() {
		return latch;
	}
	
	public ArrayList<Blog> getBlogs() {
		return blogs;
	}
	
	@KafkaListener(topics = "test")
	public void receive(Blog blog) {
		
		System.out.println(blog.toString());
		latch.countDown();
		blogs.add(blog);
	}
	
}
