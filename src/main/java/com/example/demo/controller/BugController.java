package com.example.demo.controller;


import com.example.demo.model.Ticket;
import com.example.demo.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bug")
public class BugController {
    @Autowired
    private final TicketRepository ticketRepository;

    public BugController(TicketRepository ticketRepository){
        this.ticketRepository = ticketRepository;
    }

    @GetMapping
    public List<Ticket> gettbug() {
        List<Ticket> tickets = ticketRepository.findByType(Ticket.Type.Bug);
        return tickets;
    }

    @GetMapping(value = "/{id}")
    public Ticket getbug(@PathVariable("id") String id){
        Optional<Ticket> optionalTicket = ticketRepository.findById(id);
        Ticket ticket = optionalTicket.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "bug" + id + "not found"));
        if(ticket.getType() != Ticket.Type.Bug) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "bug" + id + "not found");
        return ticket;
    }

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
