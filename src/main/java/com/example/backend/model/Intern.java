package com.example.backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Intern {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String email;

    @ManyToOne 
    @JoinColumn(name = "project_id") 
    private Project project;

    @ManyToOne
    @JoinColumn(name = "mentor_id")
    private Mentor mentor;
}