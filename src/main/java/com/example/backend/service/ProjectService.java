package com.example.backend.service;

import com.example.backend.dto.ProjectDTO;
import com.example.backend.model.Project;
import com.example.backend.repository.InternRepository;
import com.example.backend.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepo;

    @Autowired
    private InternRepository internRepo;

    @Transactional
    public Map<String, Object> createProject(ProjectDTO.Create dto) {
        Project project = new Project();
        project.setName(dto.getName());
        project.setClientName(dto.getClientName());
        project.setStartDate(dto.getStartDate());
        project.setEndDate(dto.getEndDate());
        
        projectRepo.save(project);
        
        Map<String, Object> data = new HashMap<>();
        data.put("projectId", project.getId());
        data.put("name", project.getName());
        return data;
    }

    @Transactional
    public Map<String, Object> updateProject(Long id, ProjectDTO.Update dto) {
        Project project = projectRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Project ID: " + id));
        
        project.setName(dto.getName());
        project.setClientName(dto.getClientName());
        project.setStartDate(dto.getStartDate());
        project.setEndDate(dto.getEndDate());
        
        projectRepo.save(project);
        
        Map<String, Object> data = new HashMap<>();
        data.put("projectId", project.getId());
        data.put("name", project.getName());
        return data;
    }

    @Transactional
    public Map<String, Object> deleteProject(Long id) {
        Project project = projectRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Project ID: " + id));
        
        // Kiểm tra xem dự án có intern không
        long internCount = internRepo.countByProjectId(id);
        
        if (internCount > 0) {
            throw new IllegalArgumentException("Không thể xóa dự án đang chạy");
        }
        
        projectRepo.delete(project);
        
        Map<String, Object> data = new HashMap<>();
        data.put("message", "Xóa dự án thành công");
        data.put("projectId", id);
        return data;
    }

    public List<Map<String, Object>> getAllProjects() {
        return projectRepo.findAll().stream()
                .map(p -> {
                    Map<String, Object> data = new HashMap<>();
                    data.put("id", p.getId());
                    data.put("name", p.getName());
                    data.put("clientName", p.getClientName());
                    data.put("startDate", p.getStartDate());
                    data.put("endDate", p.getEndDate());
                    data.put("internCount", internRepo.countByProjectId(p.getId()));
                    return data;
                })
                .collect(java.util.stream.Collectors.toList());
    }

    public Map<String, Object> getProjectById(Long id) {
        Project project = projectRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("không tìm thấy Project ID: " + id));
        
        Map<String, Object> data = new HashMap<>();
        data.put("id", project.getId());
        data.put("name", project.getName());
        data.put("clientName", project.getClientName());
        data.put("startDate", project.getStartDate());
        data.put("endDate", project.getEndDate());
        data.put("internCount", internRepo.countByProjectId(id));
        return data;
    }
}
