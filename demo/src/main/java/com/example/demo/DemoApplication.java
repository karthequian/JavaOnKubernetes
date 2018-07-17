package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.sql.Timestamp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SpringBootApplication
public class DemoApplication {
	static final Logger logger = LogManager.getLogger(DemoApplication.class);

	public static void main(String[] args) {
		logger.debug("Starting up application...");
		Date date = new Date();
		logger.debug("Application started up at " + new Timestamp(date.getTime()));
		SpringApplication.run(DemoApplication.class, args);
	}
}
