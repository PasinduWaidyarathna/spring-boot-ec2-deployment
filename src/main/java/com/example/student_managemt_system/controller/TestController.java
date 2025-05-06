package com.example.student_managemt_system.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
public class TestController {

    @GetMapping("/test")
    public String testEndpoint() {
        return "Hello, Spring Boot! Pasindu Waidyarathna";
    }
}

