package com.example.demo;

import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.web.bind.annotation.RequestMapping;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
public class DemoController {
    static final Logger logger = LogManager.getLogger(DemoController.class);

    @RequestMapping("/")
    public String index() {
        Date date = new Date();
        logger.debug("/ was invoked at " + new Timestamp(date.getTime()));
        return "Greetings from Spring Boot: " + (new Timestamp(date.getTime()));
    }
}