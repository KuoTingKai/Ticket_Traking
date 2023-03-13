package com.example.demo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@Document("ticket")
public class Ticket {
    @Id
    private String id;
    public enum Type{
        TestCase,
        Bug
    }
    public enum Status{
    bug, resolve
}
    private Type type;
    private Status status;
    private String summary;
    private String description;

    public Ticket(){
        this.id = Instant.now().toString();
        this.type = Type.Bug;
        this.status = Status.bug;
        this.description = "";
        this.summary = "";
    }

    public Ticket(String id, Type type, String summary, String description) {
        this.id = id;
        this.type = type;
        this.status = status.bug;
        this.summary = summary;
        this.description = description;
    }

    public String toString() {
        return String.format("Ticket: %s [%s][%s] %s", this.id, this.status, this.summary);
    }
}
