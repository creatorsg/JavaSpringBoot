package com.rookies6.myspringboot4project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MySpringBoot4ProjectApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(MySpringBoot4ProjectApplication.class);
		application.setWebApplicationType(WebApplicationType.SERVLET);
		application.run(args);
	}

	public String myBean() {
		return "Hello Bean";
	}
}