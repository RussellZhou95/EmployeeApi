package com.example.configurationservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigServerApplication.class, args);
	}




}

@RestController
class MessageRestController {

	@Value("${message}")
	private String message;
//	@Value("${info.foo}")
//	private String foo;

	@RequestMapping("/message")
	String getMessage() {
//		System.out.println(foo);
		System.out.println("This is the message");
		return this.message;
	}
}
