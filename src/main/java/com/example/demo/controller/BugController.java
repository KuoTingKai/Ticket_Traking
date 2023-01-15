package com.example.demo.controller;


import com.example.demo.model.Ticket;
import com.example.demo.repository.TicketRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bug")
public class BugController {
    private final TicketRepository ticketRepository;

    public BugController(TicketRepository ticketRepository){
        this.ticketRepository = ticketRepository;
    }

    @GetMapping(value = "/{id}")
    public Optional<Ticket> getbug(@PathVariable("id") String id){
        return ticketRepository.findById(id);
    }

//    @GetMapping
//    public List<Ticket> gettbug() {
//        return ticketRepository.findAll();
//    }

    @PostMapping
    public void addTicket(@RequestBody Ticket newticket)
    {
        Ticket ticket = new Ticket();
        ticket.setId(newticket.getId());
        ticket.setStatus(newticket.getStatus());
        ticket.setDescription(newticket.getDescription());
        ticket.setSummary(newticket.getSummary());
        ticketRepository.save(ticket);
    }
}
