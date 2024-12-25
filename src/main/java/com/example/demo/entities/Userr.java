package com.example.demo.entities;



import jakarta.persistence.*;
import lombok.*;

//import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Setter
@Getter

public class Userr {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

   }
