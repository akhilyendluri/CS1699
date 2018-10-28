package com.cs1699.project.blogspot.tests;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.cs1699.project.blogspot.controller.BlogspotController;
import com.cs1699.project.blogspot.datasource.Blog;

@RunWith(SpringRunner.class)
@ActiveProfiles()
public class BlogspotControllerTest {
	
	@InjectMocks
	private BlogspotController controller;

	@Test
	public void pingTest() {
		
		assertEquals(controller.ping(), "pong");
		
	}
	
	@Test
	public void publishMessageTest() {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Blog blog = new Blog("Akhil", "Hello Everyone", dateFormat.format(new Date()));

		assertEquals(controller.publishMessage(blog), ResponseEntity.ok().build());
		
	}
}
