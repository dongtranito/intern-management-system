package com.example.backend.service;

import com.example.backend.dto.MentorDTO;
import com.example.backend.model.Intern;
import com.example.backend.model.Mentor;
import com.example.backend.repository.InternRepository;
import com.example.backend.repository.MentorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MentorService {

    @Autowired
    private MentorRepository mentorRepo;

    @Autowired
    private InternRepository internRepo;

    @Transactional
    public Map<String, Object> createMentor(MentorDTO.Create dto) {
        Mentor mentor = new Mentor();
        mentor.setName(dto.getName());
        mentor.setSkill(dto.getSkill());
        
        Mentor saved = mentorRepo.save(mentor);
        
        Map<String, Object> data = new HashMap<>();
        data.put("id", saved.getId());
        data.put("name", saved.getName());
        data.put("skill", saved.getSkill());
        return data;
    }

    @Transactional
    public Map<String, Object> updateMentorSkill(Long id, MentorDTO.Update dto) {
        Mentor mentor = mentorRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Mentor ID: " + id));
        
        mentor.setSkill(dto.getSkill());
        Mentor updated = mentorRepo.save(mentor);
        
        Map<String, Object> data = new HashMap<>();
        data.put("id", updated.getId());
        data.put("name", updated.getName());
        data.put("skill", updated.getSkill());
        return data;
    }

    public List<Map<String, Object>> getAllMentors() {
        return mentorRepo.findAll().stream()
                .map(m -> {
                    Map<String, Object> data = new HashMap<>();
                    data.put("id", m.getId());
                    data.put("name", m.getName());
                    data.put("skill", m.getSkill());
                    return data;
                })
                .collect(Collectors.toList());
    }

    public List<Map<String, Object>> getInternsByMentorId(Long mentorId) {
        Mentor mentor = mentorRepo.findById(mentorId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Mentor ID: " + mentorId));
        
        List<Intern> interns = internRepo.findByMentorId(mentorId);
        
        return interns.stream()
                .map(i -> {
                    Map<String, Object> data = new HashMap<>();
                    data.put("id", i.getId());
                    data.put("fullName", i.getFullName());
                    data.put("email", i.getEmail());
                    data.put("projectName", i.getProject() != null ? i.getProject().getName() : null);
                    return data;
                })
                .collect(Collectors.toList());
    }
}
