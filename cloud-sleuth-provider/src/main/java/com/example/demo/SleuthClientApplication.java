package com.example.demo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SleuthClientApplication {
	public static void main(String[] args) {
		SpringApplication.run(SleuthClientApplication.class, args);
	}
}

@RestController
class HiController {
	@Autowired
	private Tracer tracer;

	private static final Log log = LogFactory.getLog(HiController.class);

	@RequestMapping("/provider")
	public String hi() throws Exception {
		log.info("-------------------------provider");
		setZipkinErrors("a", "b");
		return "provider";
	}

	public void setZipkinErrors(String span, String title) {

		Span dateServiceSpan = this.tracer.createSpan(span);

		try {
			dateServiceSpan.logEvent("_Tracer_Span_ : " + span + " _title_ : " + title);
		} finally {
			this.tracer.close(dateServiceSpan);
		}
	}
}
