package com.lion.youranmok;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class YourAnmokApplication {

	public static void main(String[] args) {

		SpringApplication.run(YourAnmokApplication.class, args);
	}

}
