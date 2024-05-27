package com.example.pbl4.controller;

import com.example.pbl4.user.User;
import com.example.pbl4.user.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;

    public LoginController(UserService userService, BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        return "login";
    }

    @PostMapping("/login")
    public String login (@RequestParam("username") String username, @RequestParam("password") String password, Model model) {
        boolean isAuthenticated = authenticate(username, password);

        if (isAuthenticated) {
            return "/index";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "/login";
        }
    }

    private boolean authenticate(String username, String password) {
        User user = userService.findUserByUsername(username);
        if (user != null) {
            return passwordEncoder.matches(password, user.getPassword());
        }
        return false;
    }
}