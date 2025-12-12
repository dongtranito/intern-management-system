package com.example.backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Mentor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; 
    private String skill; 
}