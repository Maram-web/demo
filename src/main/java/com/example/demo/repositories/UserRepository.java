package com.example.demo.repositories;

import com.example.demo.entities.Userr;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<Userr, Long> {
}
