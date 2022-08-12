package com.lion.youranmok;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@ComponentScan(basePackages = {"com.lion.youranmok"})
public class YourAnmokApplication {

	public static void main(String[] args) {

		SpringApplication.run(YourAnmokApplication.class, args);
	}

}
