package com.example.demo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document("token")
public class Token {
    @Id
    private String value;
    private Date expirationDate;

    public Token(String value, Date expirationDate) {
        this.value = value;
        this.expirationDate = expirationDate;
    }
    @Override
    public String toString() {
        return "Bearer " + value;
    }

//    @Id
//    private String id;
//    private String uid;
//
//    public Token () {
//        this.id = UUID.randomUUID().toString().replaceAll("-", "") + UUID.randomUUID().toString().replaceAll("-", "");
//        this.uid = "";
//    }
//    public Token(String id, String uid) {
//        this.id = id;
//        this.uid = uid;
//    }
//
//    public String toString() {return String.format("Token[%s] %s ", this.uid, this.id);}
}
