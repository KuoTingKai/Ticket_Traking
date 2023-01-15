package com.example.demo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.Instant;

@Data
public class Ticket {
    public enum Status{
    Prefix,
    Done
}
    @Id
    private String id;
    private Status status;
    private String summary;
    private String description;

    public Ticket(){
        this.id = Instant.now().toString();
        this.status = Status.Prefix;
        this.description = "";
        this.summary = "";
    }

    public Ticket(String id, Status status, String summary, String description) {
        this.id = id;
        this.status = status;
        this.summary = summary;
        this.description = description;
    }

    public String toString() {
        return String.format("Ticket: %s [%s][%s] %s", this.id, this.status, this.summary);
    }
}
