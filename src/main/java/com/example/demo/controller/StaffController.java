package com.example.demo.controller;

import com.example.demo.model.Staff;
import com.example.demo.repository.StaffRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/staff")
public class StaffController {
    private final StaffRepository staffRepository;

    public StaffController(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }
    @GetMapping(value = "/{id}")
    public Optional<Staff> getStaff(@PathVariable("id") String id)
    {
        return staffRepository.findById(id);
    }

    @GetMapping
    public List<Staff> getStaff()
    {
        return staffRepository.findAll();
    }

    @PostMapping
    public void addStaff(@RequestBody Staff newstaff)
    {
        staffRepository.save(new Staff(newstaff.getId(), newstaff.getType()));
    }

}
