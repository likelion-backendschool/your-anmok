package com.lion.youranmok;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.lion.youranmok"})
public class YourAnmokApplication {

	public static void main(String[] args) {

		SpringApplication.run(YourAnmokApplication.class, args);
	}

}
