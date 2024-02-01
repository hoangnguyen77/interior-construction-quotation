package com.swp.spring.interiorconstructionquotation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class HomeController {
    @GetMapping
    public String home() {
        return "redirect:/home";
    }
    @GetMapping("/home")
    public String showHomePage(){
        return "home/home";
    }
//    @GetMapping("/test")
//    public String test() {
//        return "test";
//    }
}
