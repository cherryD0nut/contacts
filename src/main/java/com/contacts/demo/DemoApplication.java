package com.contacts.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing
public class DemoApplication {

//	// war 배포를 위한 소스
//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//		return builder.sources( DemoApplication.class);
//	}
	
	public static void main (String[] args) {		
		SpringApplication.run(DemoApplication.class, args);
	}

}
