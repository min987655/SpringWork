package com.cos.scheduleex01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class Scheduleex01Application {

	public static void main(String[] args) {
		SpringApplication.run(Scheduleex01Application.class, args);
	}

}
