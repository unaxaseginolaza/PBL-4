package com.example.pbl4.user;

import com.example.pbl4.user.User;
import com.example.pbl4.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    HashMap<String, Object> datos;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return this.userRepository.findAll();
    }

    public ResponseEntity<Object> newUser(User user) {
        Optional<User> res = userRepository.findUserByUsername(user.getUsername());
        datos = new HashMap<>();

        if (res.isPresent() && user.getId()==null) {
            //throw new IllegalStateException("ya existe el producto");
            datos.put("error", true);
            datos.put("message", "Ya existe un producto con ese nombre");

            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        datos.put("message", "Se guardo con exito");
        if (user.getId()!= null) {
            datos.put("message", "Se actualizo con exito");
        }
        userRepository.save(user);
        datos.put("data", user);

        return new ResponseEntity<>(
                datos,
                HttpStatus.CREATED
        );
    }

    public ResponseEntity<Object> deleteUser(Long id) {
        boolean exists = this.userRepository.existsById(id);
        datos = new HashMap<>();

        if (!exists) {
            datos.put("error", true);
            datos.put("message", "No existe un user con esa id");

            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        userRepository.deleteById(id);
        datos.put("message", "Producto eliminado correctamente");
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );
    }
}
