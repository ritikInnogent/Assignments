package com.example.Student.Service;

import com.example.Student.DTO.CourseStudentCountDTO;
import com.example.Student.Entity.CourseEntity;
import com.example.Student.Repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CourseService implements CourseServiceInterface {

    @Autowired
    public CourseRepository courseRepository;

    @Override
    public List<CourseEntity> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public CourseEntity getCourseById(long id) {
        return courseRepository.findById(id).orElse(null);
    }

    @Override
    public CourseEntity addCourse(CourseEntity course) {
        return courseRepository.save(course);
    }

    @Override
    public CourseEntity updateCourse(long id, CourseEntity course) {
        CourseEntity existingCourse = courseRepository.findById(id).orElse(null);
        if (existingCourse != null) {
            existingCourse.setCourseName(course.getCourseName());
            existingCourse.setCourseInstructor(course.getCourseInstructor());
            existingCourse.setStudents(course.getStudents());
            return courseRepository.save(existingCourse);
        }
        return null;
    }

    @Override
    public void deleteCourse(long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public List<CourseEntity> getCourseWithoutStudent() {
        return courseRepository.getCourseWithoutStudent();
    }

    @Override
    public CourseEntity updateCourseInstructor(long courseId, String courseInstructor) {
        courseRepository.updateCourseInstructor(courseId, courseInstructor);

        return courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
    }

    @Override
    public List<CourseStudentCountDTO> getCoursesWithStudentCount() {
        return courseRepository.findAllCoursesWithStudentCount();
    }
    public List<CourseStudentCountDTO> getTopCourses(int n) {
        List<CourseStudentCountDTO> allCourses = courseRepository.findTopCourses();
        return allCourses.stream().limit(n).toList();
    }
}