package com.example.pbl4.doTask;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoTaskRepository extends JpaRepository<DoTask, Long> {
    Optional<DoTask> findProductById(Long id);
}
