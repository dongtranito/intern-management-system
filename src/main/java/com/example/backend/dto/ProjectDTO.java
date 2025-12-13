package com.example.backend.dto;

import jakarta.validation.constraints.NotBlank;
// import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

public class ProjectDTO {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Create {
        @NotBlank(message = "Tên dự án không được để trống")
        @Size(min = 2, max = 100, message = "Tên dự án phải từ 2-100 ký tự")
        private String name;

        @NotBlank(message = "Tên khách hàng không được để trống")
        @Size(min = 2, max = 100, message = "Tên khách hàng phải từ 2-100 ký tự")
        private String clientName;

        private LocalDate startDate;
        private LocalDate endDate;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Update {
        @NotBlank(message = "Tên dự án không được để trống")
        @Size(min = 2, max = 100, message = "Tên dự án phải từ 2-100 ký tự")
        private String name;

        @NotBlank(message = "Tên khách hàng không được để trống")
        @Size(min = 2, max = 100, message = "Tên khách hàng phải từ 2-100 ký tự")
        private String clientName;

        private LocalDate startDate;
        private LocalDate endDate;
    }
}
