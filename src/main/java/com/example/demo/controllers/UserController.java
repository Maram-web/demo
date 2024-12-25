package com.example.demo.controllers;



import com.example.demo.entities.Userr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.repositories.UserRepository;


import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<Userr> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping
    public Userr createUser(@RequestBody Userr user) {
        return userRepository.save(user);
    }



    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }
}
