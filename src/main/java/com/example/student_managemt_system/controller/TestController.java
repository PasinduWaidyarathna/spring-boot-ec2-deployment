package com.example.student_managemt_system.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class TestController {

    @GetMapping("/test")
    public String testEndpoint() {
        return "Hello, Spring Boot! Pasindu Waidyarathna";
    }
}

