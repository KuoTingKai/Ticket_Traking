package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
//	@Bean
//	CommandLineRunner runner(TicketRepository repository){
//		return args -> {
//			Ticket ticket = new Ticket();
//
//			repository.insert(ticket);
//		};
//	};
}
