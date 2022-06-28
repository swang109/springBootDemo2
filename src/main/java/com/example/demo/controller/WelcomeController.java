package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
class WelcomeController {

    @GetMapping("/")
    public String welcome() {

        System.out.println("shengyang welcome");
        return "welcome";
    }
}
