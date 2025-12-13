package com.example.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class InternDTO {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Create {
        @NotBlank(message = "Tên không được để trống")
        @Size(min = 2, max = 100, message = "Tên phải từ 2-100 ký tự")
        private String fullName;

        @NotBlank(message = "Email không được để trống")
        @Email(message = "Email không đúng định dạng")
        private String email;

        @NotNull(message = "Project ID không được null")
        private Long projectId;

        @NotNull(message = "Mentor ID không được null")
        private Long mentorId;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Update {
        @Size(min = 2, max = 100, message = "Tên phải từ 2-100 ký tự")
        private String fullName;

        @Email(message = "Email không đúng định dạng")
        private String email;

        private Long projectId;
        private Long mentorId;
    }

}
