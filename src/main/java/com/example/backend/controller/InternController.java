package com.example.backend.controller;

import com.example.backend.model.*;
import com.example.backend.service.InternService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// import java.util.HashMap;
// import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/api")
public class InternController {

    @Autowired
    private InternService internService;

    @PostMapping("/projects")
    public Project addProject(@RequestBody Project project) {
        return internService.createProject(project);
    }

    @PostMapping("/mentors")
    public Mentor addMentor(@RequestBody Mentor mentor) {
        return internService.createMentor(mentor);
    }

    @PostMapping("/interns")
    public Intern addIntern(
            @RequestBody Intern intern,
            @RequestParam Long projectId,
            @RequestParam Long mentorId) {
        return internService.addIntern(intern, projectId, mentorId);
    }

    @PutMapping("/interns/{id}")
    public Intern update(@PathVariable Long id, @RequestBody Intern intern) {
        return internService.updateIntern(id, intern);
    }

    @GetMapping("/interns")
    public List<Intern> getAll() {
        return internService.getAllInterns();
    }
}   