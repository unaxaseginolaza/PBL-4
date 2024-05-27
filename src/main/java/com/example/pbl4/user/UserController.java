package com.example.pbl4.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getUsers(Model model) {
        List<User> users = userService.getUsers();
        model.addAttribute("users", users);
        return "user/list"; // Retorna la vista user/list.html
    }

    /*@GetMapping("/new")
    public String createUserForm(Model model) {
        model.addAttribute("user", new User());
        return "user/new"; // Retorna la vista user/new.html
    }

    @PostMapping
    public String createUser(@ModelAttribute User user, Model model) {
        userService.newUser(user);
        return "redirect:/user"; // Redirige a la lista de clientes
    }

    @GetMapping("/edit/{id}")
    public String updateUserForm(@PathVariable("id") Long id, Model model) {
        User user = userService.findUserById(id); // Asumiendo que tienes un método para buscar por ID
        model.addAttribute("user", user);
        return "user/edit"; // Retorna la vista user/edit.html
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute User user, Model model) {
        userService.newUser(user);
        return "redirect:/user"; // Redirige a la lista de clientes
    }*/

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/user"; // Redirige a la lista de clientes
    }
}
