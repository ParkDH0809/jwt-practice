package com.example.jwt_practice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/")
    public String hello() {
        return "Hello, world!";
    }

    @GetMapping("/public")
    public String publicEndpoint() {
        return "Everyone can see this";
    }

}
