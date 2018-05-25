package com.webapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.webapp"})
public class TesterUpload1Application {
	public static void main(String[] args) {
	
		SpringApplication.run(TesterUpload1Application.class, args);
	}
}
