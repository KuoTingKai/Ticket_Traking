package com.example.demo.controller;


import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Data
@Document
public class hello {
    @RequestMapping("/")
    public String helloworld(){
        return "hellowwwww";
    }

}