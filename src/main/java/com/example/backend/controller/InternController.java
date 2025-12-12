package com.example.backend.controller;

import com.example.backend.dto.InternDTO;
import com.example.backend.model.*;
import com.example.backend.service.InternService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/api")
public class InternController extends BaseController {

    @Autowired
    private InternService internService;

    @PostMapping
    public ResponseEntity<?> addIntern(@Valid @RequestBody InternDTO dto) {
        try {
            Map<String, Object> data = internService.addIntern(dto);
            return responseSuccess(data);
        } catch (Exception e) {
            return responseError(e);
        }
    }
}