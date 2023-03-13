package com.example.demo.repository;

import com.example.demo.model.Staff;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface StaffRepository extends MongoRepository<Staff, String> {
    List<Staff> findByType(Staff.Type type);
}
