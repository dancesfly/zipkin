package com.example.demo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SleuthClientApplication {

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(SleuthClientApplication.class, args);
	}
}

@RestController
class HomeController {

	private static final Log log = LogFactory.getLog(HomeController.class);

	@Autowired
	private RestTemplate restTemplate;

	private String url = "http://localhost:8082";

	@RequestMapping("/consumer")
	public String service1() throws Exception {
		log.info("consumer");
		String s = this.restTemplate.getForObject(url + "/provider", String.class);
		return s;
	}
}