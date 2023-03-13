package com.example.demo.repository;

import com.example.demo.model.Token;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TokenRepository extends MongoRepository <Token, String> {
}
