package com.example.backend.controller;

import com.example.backend.dto.MentorDTO;
import com.example.backend.service.MentorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/api/mentors")
public class MentorController extends BaseController {

    @Autowired
    private MentorService mentorService;

    @PostMapping
    public ResponseEntity<?> createMentor(@Valid @RequestBody MentorDTO.Create dto) {
        Map<String, Object> data = mentorService.createMentor(dto);
        return responseSuccess(data);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMentorSkill(
            @PathVariable Long id,
            @Valid @RequestBody MentorDTO.Update dto) {
        Map<String, Object> data = mentorService.updateMentorSkill(id, dto);
        return responseSuccess(data);
    }

    @GetMapping
    public ResponseEntity<?> getAllMentors() {
        List<Map<String, Object>> data = mentorService.getAllMentors();
        return responseSuccess(data);
    }

    @GetMapping("/{id}/interns")
    public ResponseEntity<?> getInternsByMentor(@PathVariable Long id) {
        List<Map<String, Object>> data = mentorService.getInternsByMentorId(id);
        return responseSuccess(data);
    }
}
