package com.example.backend.service;

import com.example.backend.model.*;
import com.example.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InternService {  // chỗ này tí nữa m viết một cái baseService

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

    public Intern addIntern(Intern intern, Long projectId, Long mentorId) {
        Project project = projectRepo.findById(projectId).orElse(null);
        Mentor mentor = mentorRepo.findById(mentorId).orElse(null);
        intern.setProject(project);
        intern.setMentor(mentor);
        return internRepo.save(intern);
    }

    public Intern updateIntern(Long id, Intern newInternData) {
        Intern existingIntern = internRepo.findById(id).orElse(null);

        if (existingIntern != null) {
            existingIntern.setFullName(newInternData.getFullName());
            existingIntern.setEmail(newInternData.getEmail());
            return internRepo.save(existingIntern);
        }
        return null; 
    }

    public void deleteIntern(Long id) {
        internRepo.deleteById(id);
    }

    public List<Intern> getAllInterns() {
        return internRepo.findAll();
    }
}