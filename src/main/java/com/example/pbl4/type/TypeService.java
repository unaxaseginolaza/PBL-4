package com.example.pbl4.type;

import com.example.pbl4.type.Type;
import com.example.pbl4.type.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeService {
    private final TypeRepository typeRepository;

    @Autowired
    public TypeService(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    public List<Type> getTypes() {
        return this.typeRepository.findAll();
    }

    public Type findTypeById(Long id) {
        return typeRepository.findById(id).orElseThrow(() -> new IllegalStateException("Type not found"));
    }

    public void newType(Type type) {
        Optional<Type> res = typeRepository.findTypeByName(type.getName());
        if (res.isPresent() && type.getId() == null) {
            throw new IllegalStateException("Ya existe un empleado con name");
        }
        typeRepository.save(type);
    }

    public void deleteType(Long id) {
        boolean exists = typeRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("No existe un Type con esa id");
        }
        typeRepository.deleteById(id);
    }
}
