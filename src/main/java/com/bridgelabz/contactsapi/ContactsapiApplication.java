package com.bridgelabz.contactsapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ContactsapiApplication {

	public static final Logger logger= LoggerFactory.getLogger(ContactsapiApplication.class);
	public static void main(String[] args) {
		logger.info("Application starting...");
		SpringApplication.run(ContactsapiApplication.class, args);
		logger.info("Application started.");
	}

}
