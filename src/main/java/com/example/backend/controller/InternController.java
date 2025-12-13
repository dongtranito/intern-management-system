package com.example.backend.controller;

import com.example.backend.dto.InternDTO;
import com.example.backend.service.InternService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/api/interns")
public class InternController extends BaseController {

    @Autowired
    private InternService internService;

    @PostMapping
    public ResponseEntity<?> addIntern(@Valid @RequestBody InternDTO.Create dto) {
        Map<String, Object> data = internService.addIntern(dto);
        return responseSuccess(data);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateIntern(
            @PathVariable Long id,
            @Valid @RequestBody InternDTO.Update dto) {
        Map<String, Object> data = internService.updateIntern(id, dto);
        return responseSuccess(data);
    }

    @GetMapping
    public ResponseEntity<?> searchInterns(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Long projectId) {
        List<Map<String, Object>> data = internService.searchInterns(keyword, projectId);
        return responseSuccess(data);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteIntern(@PathVariable Long id) {
        internService.deleteIntern(id);
        Map<String, Object> data = new HashMap<>();
        data.put("message", "Đã xóa thực tập sinh thành công");
        data.put("internId", id);
        return responseSuccess(data);
    }
}