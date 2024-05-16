package com.example.pbl4.type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/type")
public class TypeController {
    private final TypeService typeService;

    @Autowired
    public TypeController(TypeService typeService) {
        this.typeService = typeService;
    }

    @GetMapping
    public List<Type> getTypes() {
        return typeService.getTypes();
    }

    @PostMapping
    public ResponseEntity<Object> createTypes(@RequestBody Type type) {
        return this.typeService.newType(type);
    }

    @PutMapping
    public ResponseEntity<Object> updateTypes(@RequestBody Type type) {
        return this.typeService.newType(type);
    }

    @DeleteMapping(path = "/{TypesId}/delete")
    public ResponseEntity<Object> deleteTypes(@PathVariable("TypesId") Long id) {
        return this.typeService.deleteType(id);
    }
}
