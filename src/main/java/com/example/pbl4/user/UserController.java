package com.example.pbl4.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody User User) {
        return this.userService.newUser(User);
    }

    @PutMapping
    public ResponseEntity<Object> updateUser(@RequestBody User User) {
        return this.userService.newUser(User);
    }

    @DeleteMapping(path = "/{UserId}/delete")
    public ResponseEntity<Object> deleteUser(@PathVariable("UserId")  Long id) {
        return this.userService.deleteUser(id);
    }
}
