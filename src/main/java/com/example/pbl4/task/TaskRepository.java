package com.example.pbl4.task;

import com.example.pbl4.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{
    Optional<Task> findTaskByTitle(String title);
}
