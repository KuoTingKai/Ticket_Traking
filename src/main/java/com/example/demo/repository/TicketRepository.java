package com.example.demo.repository;

import com.example.demo.model.Ticket;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TicketRepository extends MongoRepository<Ticket, String> {
    List<Ticket> findByType(Ticket.Type type);
}
