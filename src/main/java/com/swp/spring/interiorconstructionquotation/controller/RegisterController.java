package com.swp.spring.interiorconstructionquotation.controller;

import com.swp.spring.interiorconstructionquotation.dao.IRoleRepository;
import com.swp.spring.interiorconstructionquotation.entity.Role;
import com.swp.spring.interiorconstructionquotation.entity.User;
import com.swp.spring.interiorconstructionquotation.service.IUserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

@Controller
@RequestMapping("/register")
public class RegisterController {
    private IUserService iUserService;
    private IRoleRepository iRoleRepository;

    @Autowired
    public RegisterController(IUserService iUserService, IRoleRepository iRoleRepository) {
        this.iUserService = iUserService;
        this.iRoleRepository =iRoleRepository;
    }

    @GetMapping("/showRegisterForm")
    public String showRegisterForm(Model model){
        User user = new User();
        model.addAttribute("registerUser", user);
        return "register/form";
    }

    @InitBinder
    public void initBinder(WebDataBinder data){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        data.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @PostMapping("/process")
    public String process(@Valid @ModelAttribute("registerUser") User registerUser, BindingResult result, Model model, HttpSession session){
        String username = registerUser.getUsername();
        if (result.hasErrors()) return "register/form";

        User userExisting = iUserService.findByUsername(username);
        if (userExisting!=null){
            model.addAttribute("registerUser", new User());
            model.addAttribute("my_error", "Tài khoản đã tồn tại");
            return "register/form";
        }

        User user = new User();
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        user.setUsername(registerUser.getUsername());
        user.setPassword(bcrypt.encode(registerUser.getPassword()));
        user.setFirstName(registerUser.getFirstName());
        user.setLastName(registerUser.getLastName());
        user.setEmail(registerUser.getEmail());
        user.setPhonenumber(registerUser.getPhonenumber());
        Role defaultRole = iRoleRepository.findByName("ROLE_CUSTOMER");
        Collection<Role> roles = new ArrayList<>();
        roles.add(defaultRole);
        user.setRoles(roles);

        iUserService.save(user);

        //alert
        session.setAttribute("myuser", user);

        return "register/confirmation";
    }
}
