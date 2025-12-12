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
    public Map<String, Object> addIntern(InternDTO dto) {
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
}