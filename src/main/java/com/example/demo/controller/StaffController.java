package com.example.demo.controller;

import com.example.demo.model.Staff;
import com.example.demo.repository.StaffRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/staff")
public class StaffController {
    private final StaffRepository staffRepository;

    public StaffController(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }


    @GetMapping
    public List<Staff> getStaff()
    {
        return staffRepository.findAll();
    }

    @PostMapping
//    public void addStaff(@RequestBody String json, HttpServletRequest)
    public void addStaff(@RequestBody Staff newstaff)
    {
        Staff staff = new Staff();
        staff.setId(newstaff.getId());
        staff.setType(newstaff.getType());
        staffRepository.save(staff);
    }

}
