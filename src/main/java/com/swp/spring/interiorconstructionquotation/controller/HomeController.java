package com.swp.spring.interiorconstructionquotation.controller;

import com.swp.spring.interiorconstructionquotation.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping()
public class HomeController {
    @GetMapping
    public String showHomePage(){
        return "home";
    }
    /*@GetMapping("/password")
    public String password(Model model){
        User user= new User();
        model.addAttribute("user", user);
        return "password";
    }*/
   @GetMapping("/successpage")
    public String successpage()
    {
       return "successpage";
    }
/*
    @PostMapping("/password")
    public  String handleFormSubmission(@RequestParam("password") String password, @RequestParam("newpassword") String newPassword, @RequestParam("checkpassword") String checkPassword) {
        // Your form processing logic here
        System.out.println(password + newPassword + checkPassword);
        // Assuming success for demonstration purposes
        return "redirect:/successpage";*/


}
