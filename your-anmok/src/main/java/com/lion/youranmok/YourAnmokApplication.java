package com.lion.youranmok;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableJpaAuditing
@SpringBootApplication
@RestController
public class YourAnmokApplication {

	public static void main(String[] args) {
		SpringApplication.run(YourAnmokApplication.class, args);
	}

}
