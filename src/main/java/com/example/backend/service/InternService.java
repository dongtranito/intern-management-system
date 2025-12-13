package com.example.backend.service;

import com.example.backend.dto.InternDTO;
import com.example.backend.model.*;
import com.example.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InternService {

    @Autowired
    private InternRepository internRepo;
    @Autowired
    private ProjectRepository projectRepo;
    @Autowired
    private MentorRepository mentorRepo;

    public Project createProject(Project project) {
        return projectRepo.save(project);
    }

    public Mentor createMentor(Mentor mentor) {
        return mentorRepo.save(mentor);
    }

    @Transactional
    public Map<String, Object> addIntern(InternDTO.Create dto) {
        Intern intern = new Intern();
        intern.setFullName(dto.getFullName());
        intern.setEmail(dto.getEmail());

        Project project = projectRepo.findById(dto.getProjectId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Project ID: " + dto.getProjectId()));
        intern.setProject(project);

        Mentor mentor = mentorRepo.findById(dto.getMentorId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Mentor ID: " + dto.getMentorId()));
        intern.setMentor(mentor);
        internRepo.save(intern);
        Map<String, Object> data = new HashMap<>();
        data.put("internId", intern.getId());
        data.put("projectId", project.getId());
        data.put("mentorId", mentor.getId());
        return data;
    }

    @Transactional
    public Map<String, Object> updateIntern(Long id, InternDTO.Update dto) {
        Intern intern = internRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Intern ID: " + id));

        if (dto.getFullName() != null && !dto.getFullName().isEmpty()) {
            intern.setFullName(dto.getFullName());
        }
        if (dto.getEmail() != null && !dto.getEmail().isEmpty()) {
            intern.setEmail(dto.getEmail());
        }
        if (dto.getProjectId() != null) {
            Project project = projectRepo.findById(dto.getProjectId())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy Project ID: " + dto.getProjectId()));
            intern.setProject(project);
        }
        if (dto.getMentorId() != null) {
            Mentor mentor = mentorRepo.findById(dto.getMentorId())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy Mentor ID: " + dto.getMentorId()));
            intern.setMentor(mentor);
        }

        Intern updated = internRepo.save(intern);
        return mapToResponse(updated);
    }

    public List<Map<String, Object>> searchInterns(String keyword, Long projectId) {
        List<Intern> interns = internRepo.searchInterns(keyword, projectId);
        return interns.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteIntern(Long id) {
        Intern intern = internRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Intern ID: " + id));
        internRepo.delete(intern);
    }

    private Map<String, Object> mapToResponse(Intern intern) {
        Map<String, Object> data = new HashMap<>();
        data.put("id", intern.getId());
        data.put("fullName", intern.getFullName());
        data.put("email", intern.getEmail());
        data.put("projectId", intern.getProject() != null ? intern.getProject().getId() : null);
        data.put("projectName", intern.getProject() != null ? intern.getProject().getName() : null);
        data.put("mentorId", intern.getMentor() != null ? intern.getMentor().getId() : null);
        data.put("mentorName", intern.getMentor() != null ? intern.getMentor().getName() : null);
        return data;
    }
}