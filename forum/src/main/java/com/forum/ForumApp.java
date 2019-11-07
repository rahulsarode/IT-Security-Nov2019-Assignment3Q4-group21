package com.forum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.forum")
public class ForumApp {

	public static void main(String[] args) {
		SpringApplication.run(ForumApp.class, args);		
	}

}
