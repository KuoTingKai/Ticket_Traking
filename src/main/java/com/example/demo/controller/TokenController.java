package com.example.demo.controller;

import com.example.demo.model.Token;
import com.example.demo.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/token")
public class TokenController {

    @Autowired
    private TokenRepository tokenRepository;

//    public TokenController(TokenRepository tokenRepository) {
//        this.tokenRepository = tokenRepository;
//    }

    @GetMapping(value = "/{id}")
    public Token get(@PathVariable String id){
        Optional <Token> optionalToken = tokenRepository.findById(id);
        return optionalToken.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "token " + id + " not found"));
    }



}
