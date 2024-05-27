package com.example.pbl4.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return this.userRepository.findAll();
    }

    /*public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalStateException("User not found"));
    }

    public void newUser(User user) {
        Optional<User> res = userRepository.findUserByUsername(user.getUsername());
        if (res.isPresent() && user.getId() == null) {
            throw new IllegalStateException("Ya existe un usuario con ese username");
        }
        userRepository.save(user);
    }*/

    public void deleteUser(Long id) {
        boolean exists = userRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("No existe un User con esa id");
        }
        userRepository.deleteById(id);
    }

    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username).orElseThrow(() -> new IllegalStateException("User not found"));
    }

}
