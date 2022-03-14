package com.fab.fabH;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.fab.fabH")
@EntityScan("com.fab.fabH")
public class FabHApplication {

	public static void main(String[] args) {
		SpringApplication.run(FabHApplication.class, args);
	}

}
