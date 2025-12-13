package com.example.backend.controller;

import com.example.backend.dto.ProjectDTO;
import com.example.backend.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/projects")
public class ProjectController extends BaseController {
    
    @Autowired
    private ProjectService projectService;
    
    @PostMapping
    public ResponseEntity<?> createProject(@Valid @RequestBody ProjectDTO.Create dto) {
        Map<String, Object> data = projectService.createProject(dto);
        return responseSuccess(data);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProject(@PathVariable Long id, @Valid @RequestBody ProjectDTO.Update dto) {
        Map<String, Object> data = projectService.updateProject(id, dto);
        return responseSuccess(data);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable Long id) {
        Map<String, Object> data = projectService.deleteProject(id);
        return responseSuccess(data);
    }
    
    @GetMapping
    public ResponseEntity<?> getAllProjects() {
        List<Map<String, Object>> projects = projectService.getAllProjects();
        return responseSuccess(projects);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getProjectById(@PathVariable Long id) {
        Map<String, Object> project = projectService.getProjectById(id);
        return responseSuccess(project);
    }
}
