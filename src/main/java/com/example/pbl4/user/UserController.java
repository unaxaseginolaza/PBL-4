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

    // Muestra la lista de usuarios, empleados y clientes
    @GetMapping
    public String getUsers(Model model) {
        List<User> users = userService.getUsers();
        model.addAttribute("users", users);
        return "user/list"; // Retorna la vista user/list.html
    }

    //No he puesto el create y update por que solo se podra crear y actualizar un employee o un client pero no un user


    // Elimina un usuario indiferentemente si es empleado o cliente
    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/user"; // Redirige a la lista de clientes
    }
}
