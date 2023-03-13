package com.example.demo.controller;


import com.example.demo.model.Staff;
import com.example.demo.model.Ticket;
import com.example.demo.model.Token;
import com.example.demo.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.Optional;

@RestController
@RequestMapping("/api/ticket")
public class BugController {
    @Autowired
    private TicketRepository ticketRepository;

    @GetMapping(value = "/{id}")
    public Optional<Ticket> Get(@PathVariable("id") String id) {
        Optional<Staff> optionalStaff = Verity_staff(id);
        Staff.Type staffType = optionalStaff.get().getType();
//        Staff.Type staffType = optionalStaff.orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED)).getType();
        if(staffType != Staff.Type.QA) throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Staff:" + id + "is not QA");
        Optional<Ticket> ticket = ticketRepository.findById(id);
        Ticket.Status ticketStatus = ticket.get().getStatus();
        if (ticketStatus != Ticket.Status.bug) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ticket:" + id + "is not bug");
        return ticket;
    }

    @DeleteMapping(value = "/{id}")
    public void Delete(@RequestHeader("Authorization") String bearerToken, @PathVariable("id") String id){
        Optional<Staff> optionalStaff = Verity_staff(bearerToken);
        Staff.Type staffType = optionalStaff.get().getType();
        if (staffType != Staff.Type.QA) throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        Optional<Ticket> ticketOptional = ticketRepository.findById(id);
        ticketOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Bug:" + id + "is not found"));
        if (ticketOptional.get().getType() != Ticket.Type.Bug) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        ticketRepository.deleteById(id);
    }

    @PatchMapping(value = "/{id}")
    public Ticket Update(@RequestHeader("Authorization") String bearerToken, @PathVariable("id") String id, @RequestBody Ticket newticket){
        Optional<Staff> staffOptional = Verity_staff(bearerToken);
        Staff.Type staffType = staffOptional.get().getType();
        if(staffType != Staff.Type.QA) throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        Optional<Ticket> ticketOptional = ticketRepository.findById(id);
        ticketOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ticket:" + id + "is not found"));
        Ticket ticket = ticketRepository.save(new Ticket(Instant.now().toString(), newticket.getType(), newticket.getSummary(), newticket.getDescription()));
        return ticket;
    }


    @PostMapping
    public void Create(@RequestHeader("Authorization") String bearerToken, @PathVariable("id") String id, @RequestBody Ticket newticket)
//    public void createTicket(@PathVariable("id") String id, @RequestBody Ticket newticket)
    {
        Optional<Staff> staffOptional = Verity_staff(bearerToken);
        Staff.Type staffType = staffOptional.get().getType();
        if(staffType != Staff.Type.QA)throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Staff:" + id + "is not QA");
        Ticket ticket = ticketRepository.save(new Ticket(Instant.now().toString(), newticket.getType(), newticket.getSummary(), newticket.getDescription()));
    }

    public Optional<Staff> Verity_staff(String bearerToken){

        if (!bearerToken.startsWith("Bearer ")) return Optional.empty();
        String id = bearerToken.split(" ")[1];
        Token token;
        RestTemplate restTemplate = new RestTemplate();

        try {
            token = restTemplate.getForObject("http://demo:8080/api/token/{id}", Token.class, id);
        }
        catch (RestClientResponseException e){
            if(HttpStatus.valueOf(e.getRawStatusCode()).series() == HttpStatus.Series.CLIENT_ERROR) return Optional.empty();
            else throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");
        }

        try {
            Staff staff = restTemplate.getForObject("http://demo:8080/api/staff/{id}", Staff.class, token.getUid());
            return Optional.of(staff);
        }
        catch (RestClientResponseException e){
            if(HttpStatus.valueOf(e.getRawStatusCode()).series() == HttpStatus.Series.CLIENT_ERROR) return Optional.empty();
            else throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");
        }
//        Staff staff = restTemplate.getForObject("http://demo:8080/api/staff/{id}", Staff.class);
//        return Optional.of(staff);
    }
}
//    public Staff Verity_staff(String id){
//        Optional<Staff> staff = staffRepository.findById(id);
//        return staff;
//    }


//    @GetMapping(value = "/{id}")
//    public Ticket getbug(@PathVariable("id") String id){
//        Optional<Ticket> optionalTicket = ticketRepository.findById(id);
//        Ticket ticket = optionalTicket.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "bug" + id + "not found"));
//        if(ticket.getType() != Ticket.Type.Bug) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "bug" + id + "not found");
//        return ticket;
//    }