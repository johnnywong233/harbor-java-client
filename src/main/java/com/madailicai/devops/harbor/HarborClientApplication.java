package com.madailicai.devops.harbor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yangxueying on 2016/11/14.
 */
@SpringBootApplication
@Configuration
public class HarborClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(HarborClientApplication.class, args);
	}
}
