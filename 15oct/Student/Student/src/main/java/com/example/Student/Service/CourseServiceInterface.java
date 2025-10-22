package com.example.Student.Service;

import com.example.Student.DTO.CourseStudentCountDTO;
import com.example.Student.Entity.CourseEntity;

import java.util.List;

public interface CourseServiceInterface {
    List<CourseEntity> getAllCourses();
    CourseEntity getCourseById(long id);
    CourseEntity addCourse(CourseEntity course);
    CourseEntity updateCourse(long id, CourseEntity course);
    void deleteCourse(long id);
    List<CourseEntity> getCourseWithoutStudent();
    CourseEntity updateCourseInstructor(long id,String courseInstructor);
    public List<CourseStudentCountDTO> getCoursesWithStudentCount();
    public List<CourseStudentCountDTO> getTopCourses(int n);
}