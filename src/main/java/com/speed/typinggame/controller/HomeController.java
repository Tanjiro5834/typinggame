package com.speed.typinggame.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Typing Game API is running 🚀";
    }

    @GetMapping("/test")
    public String test() {
        return "API working 🔥";
    }
}