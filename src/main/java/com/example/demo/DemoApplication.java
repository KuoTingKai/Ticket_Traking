package com.example.demo;

import com.example.demo.model.Staff;
import com.example.demo.model.Ticket;
import com.example.demo.repository.StaffRepository;
import com.example.demo.repository.TicketRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
