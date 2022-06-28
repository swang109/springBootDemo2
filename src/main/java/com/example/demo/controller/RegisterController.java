package com.example.demo.controller;

import com.example.demo.model.Customer;
import com.example.demo.model.UserRole;
import com.example.demo.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RegisterController {
    private RegisterService registerService;

    @Autowired
    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping("/register/guest")
    public void addGuest(@RequestBody Customer customer) {
        registerService.add(customer, UserRole.ROLE_GUEST);
    }

    @GetMapping("/customer")
    public String retrieveCustomer(String username) {
        return registerService.retrieve(username);
    }

    @GetMapping("/customers")
    public List<Customer> retrieveAllCustomer() {
        return registerService.retrieveAll();
    }

    @PostMapping("/register/host")
    public void addHost(@RequestBody Customer customer) {
        registerService.add(customer, UserRole.ROLE_HOST);
    }

}
