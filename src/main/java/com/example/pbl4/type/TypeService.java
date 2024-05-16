package com.example.pbl4.type;

import com.example.pbl4.type.Type;
import com.example.pbl4.type.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class TypeService {
    private final TypeRepository typeRepository;
    HashMap<String, Object> datos;

    @Autowired
    public TypeService(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    public List<Type> getTypes() {
        return this.typeRepository.findAll();
    }

    public ResponseEntity<Object> newType(Type type) {
        Optional<Type> res = typeRepository.findTypeByName(type.getName());
        datos = new HashMap<>();

        if (res.isPresent() && type.getId() == null) {
            //throw new IllegalStateException("ya existe el producto");
            datos.put("error", true);
            datos.put("message", "Ya existe un producto con ese nombre");

            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        datos.put("message", "Se guardo con exito");
        if (type.getId() != null) {
            datos.put("message", "Se actualizo con exito");
        }
        typeRepository.save(type);
        datos.put("data", type);

        return new ResponseEntity<>(
                datos,
                HttpStatus.CREATED
        );
    }

    public ResponseEntity<Object> deleteType(Long id) {
        boolean exists = this.typeRepository.existsById(id);
        datos = new HashMap<>();

        if (!exists) {
            datos.put("error", true);
            datos.put("message", "No existe un Type con esa id");

            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        typeRepository.deleteById(id);
        datos.put("message", "Producto eliminado correctamente");
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );
    }
}
