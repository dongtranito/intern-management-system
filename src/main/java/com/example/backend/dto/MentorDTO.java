package com.example.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class MentorDTO {
    
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Create {
        @NotBlank(message = "Tên không được để trống")
        @Size(min = 2, max = 100, message = "Tên phải từ 2-100 ký tự")
        private String name;

        @NotBlank(message = "Kỹ năng không được để trống")
        @Size(min = 2, max = 100, message = "Kỹ năng phải từ 2-100 ký tự")
        private String skill;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Update {
        @NotBlank(message = "Kỹ năng không được để trống")
        @Size(min = 2, max = 100, message = "Kỹ năng phải từ 2-100 ký tự")
        private String skill;
    }

}
