package com.cg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.cg.model.ApplicationUser;
import com.cg.model.UserDto;
import com.cg.repository.UserRepository;

@Controller
public class HomeController {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/homepage")
    public String homepage(Model model) {
        List<ApplicationUser> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "homepage";
    }

    @GetMapping("/login.html")
    public String login() {
        return "login";
    }
    
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserDto());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") UserDto userRegistrationDto) {
        // Create a new ApplicationUser entity and set its values
        ApplicationUser user = new ApplicationUser();
        user.setFullName(userRegistrationDto.getFullName());
        user.setUsername(userRegistrationDto.getUsername());
        user.setEmail(userRegistrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword())); // Encode the password

        userRepository.save(user);
        
        return "redirect:/register?registered=true";
    }
}