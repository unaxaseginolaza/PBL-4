package com.example.pbl4.doTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class DoTaskService {
    private final DoTaskRepository doTaskRepository;
    HashMap<String, Object> datos;

    @Autowired
    public DoTaskService(DoTaskRepository doTaskRepository) {
        this.doTaskRepository = doTaskRepository;
    }

    public List<DoTask> getDoTasks() {
        return this.doTaskRepository.findAll();
    }

    public ResponseEntity<Object> newDoTask(DoTask doTask) {
        Optional<DoTask> res = doTaskRepository.findProductById(doTask.getId());
        datos = new HashMap<>();

        if (res.isPresent() && doTask.getId()==null) {
            //throw new IllegalStateException("ya existe el producto");
            datos.put("error", true);
            datos.put("message", "Ya existe un producto con ese nombre");

            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        datos.put("message", "Se guardo con exito");
        if (doTask.getId()!= null) {
            datos.put("message", "Se actualizo con exito");
        }
        doTaskRepository.save(doTask);
        datos.put("data", doTask);

        return new ResponseEntity<>(
                datos,
                HttpStatus.CREATED
        );
    }

    public ResponseEntity<Object> deleteDoTask(Long id) {
        boolean exists = this.doTaskRepository.existsById(id);
        datos = new HashMap<>();

        if (!exists) {
            datos.put("error", true);
            datos.put("message", "No existe un DoTask con esa id");

            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        doTaskRepository.deleteById(id);
        datos.put("message", "Producto eliminado correctamente");
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );
    }
}
