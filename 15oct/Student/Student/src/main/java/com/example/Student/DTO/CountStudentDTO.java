package com.example.Student.DTO;
import lombok.Data;

@Data

public class CountStudentDTO {
    private Long courseId;
    private String courseName;
    private Long count;

    public CountStudentDTO(Long courseId, String courseName, Long count) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.count = count;
    }
}