package com.example.Student.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CourseStudentCountDTO {
    private Long courseId;
    private String courseName;
    private String courseInstructor;
    private Long studentCount;

}